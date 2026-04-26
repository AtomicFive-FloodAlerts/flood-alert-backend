package Atomic5.demo.service;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.AlertStatus;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.AlertRepository;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.util.LocationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final FloodSeverityService floodSeverityService;

    public AlertService(AlertRepository alertRepository,
                        UserRepository userRepository,
                        FloodSeverityService floodSeverityService) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.floodSeverityService = floodSeverityService;
    }

    @Transactional
    public List<Alert> generateAlertsForFloodReport(FloodReport floodReport) {
        List<Alert> generatedAlerts = new ArrayList<>();

        double alertRadiusKm = floodSeverityService.getAlertRadiusKm(floodReport.getSeverity());
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            if (user.getId().equals(floodReport.getReportedByUserId())
                    || !Boolean.TRUE.equals(user.getNotificationsEnabled())
                    || user.getLatitude() == null
                    || user.getLongitude() == null) {
                continue;
            }

            if (isUserInAlertRadius(user, floodReport, alertRadiusKm)) {
                Alert alert = createAlert(user, floodReport);
                generatedAlerts.add(alertRepository.save(alert));
            }
        }

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
        return alertRepository.findByRecipientOrderByCreatedAtDesc(user);
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
}