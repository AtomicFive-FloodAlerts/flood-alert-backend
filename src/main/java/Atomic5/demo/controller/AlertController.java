package Atomic5.demo.controller;

import Atomic5.demo.dto.AlertDTO;
import Atomic5.demo.model.Alert;
import Atomic5.demo.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Alert Controller
 * Manages REST endpoints for flood alert operations.
 * Provides functionality to retrieve, mark, acknowledge, and manage alerts for
 * users.
 * All endpoints require user authentication via JWT token.
 */
@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    /**
     * Constructor for AlertController
     * 
     * @param alertService - Service for alert operations
     */
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
        System.out.println("AlertController initialized");
    }

    /**
     * Retrieve all alerts for a specific user
     * 
     * @param userId - ID of the user
     * @return ResponseEntity containing list of AlertDTOs for the user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertDTO>> getAlertsForUser(@PathVariable Long userId) {
        // Fetch all alerts for the user from database
        List<Alert> alerts = alertService.getAlertsForUser(userId);
        // Convert Alert entities to DTOs for response
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    /**
     * Get count of unread alerts for a user
     * 
     * @param userId - ID of the user
     * @return ResponseEntity containing the count of unread alerts
     */
    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadAlertCount(@PathVariable Long userId) {
        // Query unread alert count for user
        long count = alertService.getUnreadAlertCount(userId);
        return ResponseEntity.ok(count);
    }

    /**
     * Mark an alert as read
     * 
     * @param alertId - ID of the alert to mark as read
     * @return ResponseEntity containing the updated AlertDTO, or 404 if not found
     */
    @PutMapping("/{alertId}/read")
    public ResponseEntity<AlertDTO> markAlertAsRead(@PathVariable Long alertId) {
        // Mark the alert as read and get the updated alert
        Alert alert = alertService.markAsRead(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Dismiss an alert (removes it from active notifications)
     * 
     * @param alertId - ID of the alert to dismiss
     * @return ResponseEntity containing the updated AlertDTO, or 404 if not found
     */
    @PutMapping("/{alertId}/dismiss")
    public ResponseEntity<AlertDTO> dismissAlert(@PathVariable Long alertId) {
        // Dismiss the alert to mark it as no longer needed
        Alert alert = alertService.dismissAlert(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete an alert permanently
     * 
     * @param alertId - ID of the alert to delete
     * @return ResponseEntity with no content on success, or 404 if not found
     */
    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        // Permanently delete the alert from database
        boolean deleted = alertService.deleteAlert(alertId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Acknowledge an alert (user has seen and understood the alert)
     * 
     * @param alertId - ID of the alert to acknowledge
     * @return ResponseEntity containing the updated AlertDTO, or 404 if not found
     */
    @PutMapping("/{alertId}/acknowledge")
    public ResponseEntity<AlertDTO> acknowledgeAlert(@PathVariable Long alertId) {
        // Mark alert as acknowledged by user
        Alert alert = alertService.acknowledgeAlert(alertId);
        if (alert != null) {
            return ResponseEntity.ok(convertToDTO(alert));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get alerts filtered by severity level
     * 
     * @param userId   - ID of the user
     * @param severity - Severity level filter (e.g., LOW, MEDIUM, HIGH, EXTREME)
     * @return ResponseEntity containing list of AlertDTOs matching the severity
     */
    @GetMapping("/user/{userId}/severity/{severity}")
    public ResponseEntity<List<AlertDTO>> getAlertsBySeverity(
            @PathVariable Long userId,
            @PathVariable String severity) {
        // Fetch alerts filtered by severity for the user
        List<Alert> alerts = alertService.getAlertsBySeverity(userId, severity);
        // Convert to DTOs for response
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    /**
     * Get active alerts for a user (unread or acknowledged but not dismissed)
     * 
     * @param userId - ID of the user
     * @return ResponseEntity containing list of active AlertDTOs
     */
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<AlertDTO>> getActiveAlerts(@PathVariable Long userId) {
        // Fetch all active (non-dismissed) alerts for the user
        List<Alert> alerts = alertService.getActiveAlerts(userId);
        // Convert to DTOs for response
        List<AlertDTO> alertDTOs = alerts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertDTOs);
    }

    /**
     * Convert an Alert entity to AlertDTO for response
     * Extracts relevant information and handles null values safely
     * 
     * @param alert - Alert entity to convert
     * @return AlertDTO with populated fields from the alert entity
     */
    private AlertDTO convertToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        // Copy basic alert properties
        dto.setId(alert.getId());
        dto.setFloodReportId(alert.getFloodReportId());
        // Extract recipient ID safely (check for null)
        dto.setRecipientId(alert.getRecipient() != null ? alert.getRecipient().getId() : null);
        dto.setTitle(alert.getTitle());
        dto.setMessage(alert.getMessage());
        // Convert status enum to string
        dto.setStatus(alert.getStatus().name());
        // Convert timestamps safely (check for null)
        dto.setCreatedAt(alert.getCreatedAt() != null ? alert.getCreatedAt().toString() : null);
        dto.setReadAt(alert.getReadAt() != null ? alert.getReadAt().toString() : null);
        // Include distance from user for location-based alerts
        dto.setDistanceKm(alert.getDistanceKm());

        return dto;
    }
}