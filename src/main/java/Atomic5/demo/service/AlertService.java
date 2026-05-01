package Atomic5.demo.service;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.AlertStatus;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.AlertRepository;
import Atomic5.demo.repository.FloodReportRepository;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.util.LocationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    private final AlertRepository alertRepository;
    private final FloodReportRepository floodReportRepository;
    private final UserRepository userRepository;
    private final FloodSeverityService floodSeverityService;
    private final NotificationService notificationService;

    public AlertService(AlertRepository alertRepository,
                        FloodReportRepository floodReportRepository,
                        UserRepository userRepository,
                        FloodSeverityService floodSeverityService,
                        NotificationService notificationService) {
        this.alertRepository = alertRepository;
        this.floodReportRepository = floodReportRepository;
        this.userRepository = userRepository;
        this.floodSeverityService = floodSeverityService;
        this.notificationService = notificationService;
    }

    @Transactional
    public List<Alert> generateAlertsForFloodReport(FloodReport floodReport) {
        List<Alert> generatedAlerts = new ArrayList<>();

        double alertRadiusKm = floodSeverityService.getAlertRadiusKm(floodReport.getSeverity());
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            if (user.getId().equals(floodReport.getReportedById())
                    || !Boolean.TRUE.equals(user.getNotificationsEnabled())
                    || user.getLatitude() == null
                    || user.getLongitude() == null) {
                continue;
            }

            if (isUserInAlertRadius(user, floodReport, alertRadiusKm)) {
                Alert alert = createAlert(user, floodReport);
                Alert savedAlert = alertRepository.save(alert);
                savedAlert.setFloodReport(floodReport);
                generatedAlerts.add(savedAlert);

                try {
                    notificationService.sendAlertNotification(savedAlert, user);
                } catch (Exception e) {
                    logger.warn("Failed to send notification for alert {}: {}", savedAlert.getId(), e.getMessage());
                }
            }
        }

        logger.info("Generated {} alerts for flood report {}", generatedAlerts.size(), floodReport.getId());
        return generatedAlerts;
    }

    private boolean isUserInAlertRadius(User user, FloodReport flood, double radiusKm) {
        if (user.getLatitude() == null || user.getLongitude() == null
                || flood.getLatitude() == null || flood.getLongitude() == null) {
            return false;
        }

        return LocationUtil.isWithinRadius(
                user.getLatitude(), user.getLongitude(),
                flood.getLatitude(), flood.getLongitude(),
                radiusKm);
    }

    private Alert createAlert(User user, FloodReport floodReport) {
        Alert alert = new Alert();
        alert.setRecipient(user);
        alert.setFloodReportId(floodReport.getId());
        alert.setStatus(AlertStatus.UNREAD);

        double distanceKm = LocationUtil.calculateDistance(
                user.getLatitude(), user.getLongitude(),
                floodReport.getLatitude(), floodReport.getLongitude());
        alert.setDistanceKm(distanceKm);

        String title = "Flood Alert - " + floodReport.getSeverity().name();
        String message = String.format(
                "⚠️ %s reported near %s at distance %.1f km. %s",
                floodReport.getSeverity().name(),
                floodReport.getAreaName(),
                distanceKm,
                floodReport.getDescription());

        alert.setTitle(title);
        alert.setMessage(message);

        return alert;
    }

    public List<Alert> getAlertsForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        List<Alert> alerts = alertRepository.findByRecipientOrderByCreatedAtDesc(user);
        attachFloodReports(alerts);
        return alerts;
    }

    public long getUnreadAlertCount(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return 0;
        }
        return alertRepository.countUnreadAlerts(user);
    }

    @Transactional
    public Alert markAsRead(Long alertId) {
        Alert alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null && alert.getStatus() == AlertStatus.UNREAD) {
            alert.setStatus(AlertStatus.READ);
            alert.setReadAt(LocalDateTime.now());
            return alertRepository.save(alert);
        }
        return alert;
    }

    @Transactional
    public Alert dismissAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setStatus(AlertStatus.DISMISSED);
            return alertRepository.save(alert);
        }
        return alert;
    }

    @Transactional
    public Alert acknowledgeAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setStatus(AlertStatus.ACKNOWLEDGED);
            alert.setReadAt(LocalDateTime.now());
            return alertRepository.save(alert);
        }
        return alert;
    }

    @Transactional
    public boolean deleteAlert(Long alertId) {
        try {
            if (!alertRepository.existsById(alertId)) {
                return false;
            }
            alertRepository.deleteById(alertId);
            logger.info("Alert {} deleted successfully", alertId);
            return true;
        } catch (Exception e) {
            logger.error("Failed to delete alert {}: {}", alertId, e.getMessage());
            return false;
        }
    }

    public List<Alert> getAlertsBySeverity(Long userId, String severity) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }

        List<Alert> allAlerts = alertRepository.findByRecipientOrderByCreatedAtDesc(user);
        attachFloodReports(allAlerts);
        return allAlerts.stream()
                .filter(alert -> alert.getFloodReport() != null
                        && alert.getFloodReport().getSeverity().name().equalsIgnoreCase(severity))
                .toList();
    }

    public List<Alert> getActiveAlerts(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }

        List<Alert> allAlerts = alertRepository.findByRecipientOrderByCreatedAtDesc(user);
        attachFloodReports(allAlerts);
        return allAlerts.stream()
                .filter(alert -> alert.getStatus() == AlertStatus.UNREAD
                        || alert.getStatus() == AlertStatus.ACKNOWLEDGED)
                .toList();
    }

    private void attachFloodReports(List<Alert> alerts) {
        for (Alert alert : alerts) {
            if (alert.getFloodReport() != null || alert.getFloodReportId() == null) {
                continue;
            }
            floodReportRepository.findById(alert.getFloodReportId())
                    .ifPresent(alert::setFloodReport);
        }
    }
}