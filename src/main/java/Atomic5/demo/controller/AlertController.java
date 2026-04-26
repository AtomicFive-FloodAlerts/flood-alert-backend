package Atomic5.demo.controller;

import Atomic5.demo.dto.AlertDTO;
import Atomic5.demo.model.Alert;
import Atomic5.demo.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * Get all alerts for a specific user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertDTO>> getAlertsForUser(@PathVariable Long userId) {
        List<Alert> alerts = alertService.getAlertsForUser(userId);
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    /**
     * Get unread alert count for a user
     */
    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadAlertCount(@PathVariable Long userId) {
        long count = alertService.getUnreadAlertCount(userId);
        return ResponseEntity.ok(count);
    }

    /**
     * Mark an alert as read
     */
    @PutMapping("/{alertId}/read")
    public ResponseEntity<AlertDTO> markAlertAsRead(@PathVariable Long alertId) {
        Alert alert = alertService.markAsRead(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Dismiss an alert
     */
    @PutMapping("/{alertId}/dismiss")
    public ResponseEntity<AlertDTO> dismissAlert(@PathVariable Long alertId) {
        Alert alert = alertService.dismissAlert(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete an alert
     */
    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        boolean deleted = alertService.deleteAlert(alertId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Acknowledge an alert
     */
    @PutMapping("/{alertId}/acknowledge")
    public ResponseEntity<AlertDTO> acknowledgeAlert(@PathVariable Long alertId) {
        Alert alert = alertService.acknowledgeAlert(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get alerts by severity level
     */
    @GetMapping("/user/{userId}/severity/{severity}")
    public ResponseEntity<List<AlertDTO>> getAlertsBySeverity(
            @PathVariable Long userId,
            @PathVariable String severity) {
        List<Alert> alerts = alertService.getAlertsBySeverity(userId, severity);
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    /**
     * Get active alerts (unread or acknowledged)
     */
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<AlertDTO>> getActiveAlerts(@PathVariable Long userId) {
        List<Alert> alerts = alertService.getActiveAlerts(userId);
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    private AlertDTO convertToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId(alert.getId());
        dto.setFloodReportId(alert.getFloodReport() != null ? alert.getFloodReport().getId() : null);
        dto.setRecipientId(alert.getRecipient() != null ? alert.getRecipient().getId() : null);
        dto.setTitle(alert.getTitle());
        dto.setMessage(alert.getMessage());
        dto.setStatus(alert.getStatus().name());
        dto.setCreatedAt(alert.getCreatedAt() != null ? alert.getCreatedAt().toString() : null);
        dto.setReadAt(alert.getReadAt() != null ? alert.getReadAt().toString() : null);
        dto.setDistanceKm(alert.getDistanceKm());

        if (alert.getFloodReport() != null) {
            dto.setAreaName(alert.getFloodReport().getAreaName());
            dto.setFloodSeverity(alert.getFloodReport().getSeverity().name());
        }

        return dto;
    }
}
