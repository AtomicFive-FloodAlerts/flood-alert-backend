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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to generate and manage alerts for flood events
 */
@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final FloodSeverityService floodSeverityService;
    private final NotificationService notificationService;

    public AlertService(AlertRepository alertRepository, UserRepository userRepository,
            FloodSeverityService floodSeverityService, NotificationService notificationService) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.floodSeverityService = floodSeverityService;
        this.notificationService = notificationService;
    }

    /**
     * Generate alerts for all nearby users based on flood report
     */
    @Transactional
    public List<Alert> generateAlertsForFloodReport(FloodReport floodReport) {
        List<Alert> generatedAlerts = new ArrayList<>();

        // Get alert radius based on flood severity
        double alertRadiusKm = floodSeverityService.getAlertRadiusKm(floodReport.getSeverity());

        // Get all active users
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            // Skip if user is the reporter or disabled notifications
            if (user.getId().equals(floodReport.getReportedBy().getId()) ||
                    !user.getNotificationsEnabled()) {
                continue;
            }

            // Check if user is within alert radius
            if (isUserInAlertRadius(user, floodReport, alertRadiusKm)) {
                Alert alert = createAlert(user, floodReport, alertRadiusKm);
                Alert savedAlert = alertRepository.save(alert);
                generatedAlerts.add(savedAlert);

                // Send notification to user
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

    /**
     * Check if a user is within the alert radius of a flood
     */
    private boolean isUserInAlertRadius(User user, FloodReport flood, double radiusKm) {
        return LocationUtil.isWithinRadius(
                user.getLatitude(), user.getLongitude(),
                flood.getLatitude(), flood.getLongitude(),
                radiusKm);
    }

    /**
     * Create an alert object for a user based on flood report
     */
    private Alert createAlert(User user, FloodReport floodReport, double radiusKm) {
        Alert alert = new Alert();
        alert.setRecipient(user);
        alert.setFloodReport(floodReport);
        alert.setStatus(AlertStatus.UNREAD);

        double distanceKm = LocationUtil.calculateDistance(
                user.getLatitude(), user.getLongitude(),
                floodReport.getLatitude(), floodReport.getLongitude());
        alert.setDistanceKm(distanceKm);

        // Generate alert message
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

    /**
     * Get all alerts for a user
     */
    public List<Alert> getAlertsForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        return alertRepository.findByRecipientOrderByCreatedAtDesc(user);
    }

    /**
     * Get unread alert count for a user
     */
    public long getUnreadAlertCount(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return 0;
        }
        return alertRepository.countUnreadAlerts(user);
    }

    /**
     * Mark alert as read
     */
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

    /**
     * Dismiss alert
     */
    @Transactional
    public Alert dismissAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setStatus(AlertStatus.DISMISSED);
            return alertRepository.save(alert);
        }
        return alert;
    }

    /**
     * Acknowledge alert
     */
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

    /**
     * Delete alert
     */
    @Transactional
    public boolean deleteAlert(Long alertId) {
        try {
            alertRepository.deleteById(alertId);
            logger.info("Alert {} deleted successfully", alertId);
            return true;
        } catch (Exception e) {
            logger.error("Failed to delete alert {}: {}", alertId, e.getMessage());
            return false;
        }
    }

    /**
     * Get alerts by severity level
     */
    public List<Alert> getAlertsBySeverity(Long userId, String severity) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        List<Alert> allAlerts = alertRepository.findByRecipientOrderByCreatedAtDesc(user);
        return allAlerts.stream()
                .filter(alert -> alert.getFloodReport() != null && 
                        alert.getFloodReport().getSeverity().name().equals(severity))
                .toList();
    }

    /**
     * Get active alerts (unread or acknowledged)
     */
    public List<Alert> getActiveAlerts(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        List<Alert> allAlerts = alertRepository.findByRecipientOrderByCreatedAtDesc(user);
        return allAlerts.stream()
                .filter(alert -> alert.getStatus() == AlertStatus.UNREAD || 
                        alert.getStatus() == AlertStatus.ACKNOWLEDGED)
                .toList();
    }
}
