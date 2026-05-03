package Atomic5.demo.controller;

import Atomic5.demo.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

/**
 * SMS Notification Controller
 * Manages REST endpoints for SMS notifications.
 * Provides functionality to check SMS provider status and send test SMS
 * messages.
 * Uses optional SmsService which may not be available if no SMS providers are
 * configured.
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    // Optional SMS service - injected if available, null otherwise
    @Autowired(required = false)
    private SmsService smsService;

    /**
     * Get SMS provider status and configuration
     * Returns information about available SMS providers and their status
     * 
     * @return ResponseEntity with SMS service status information
     */
    @GetMapping("/sms-status")
    public ResponseEntity<?> getSmsStatus() {
        Map<String, Object> response = new HashMap<>();

        // Check if SMS service is available (optional dependency)
        if (smsService == null) {
            logger.warn("SMS service not available - check configuration");
            response.put("status", "SMS service not available");
            response.put("providers", new String[] {});
            response.put("configured", 0);
            return ResponseEntity.ok(response);
        }

        // SMS service is available - return provider details
        response.put("status", smsService.getProviderStatus());
        response.put("availableProviders", smsService.getAvailableProviders());
        response.put("isConfigured", smsService.isAnySmsProviderConfigured());
        logger.info("SMS status requested - providers available: {}", smsService.getAvailableProviders());

        return ResponseEntity.ok(response);
    }

    /**
     * Send a test SMS message
     * Validates SMS service availability and required parameters
     * 
     * @param request - Map containing 'phoneNumber' and 'message' fields
     * @return ResponseEntity with success/failure status and masked phone number
     */
    @PostMapping("/test-sms")
    public ResponseEntity<?> sendTestSms(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        // Check if SMS service is available (optional dependency)
        if (smsService == null) {
            logger.error("SMS service not available - cannot send test SMS");
            response.put("success", false);
            response.put("message", "SMS service not available");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if at least one SMS provider is configured
        if (!smsService.isAnySmsProviderConfigured()) {
            logger.error("No SMS providers configured");
            response.put("success", false);
            response.put("message", "No SMS providers configured");
            return ResponseEntity.badRequest().body(response);
        }

        // Extract and validate phone number from request
        String phoneNumber = request.get("phoneNumber");
        String message = request.get("message");

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Phone number is required");
            return ResponseEntity.badRequest().body(response);
        }

        // Validate message content
        if (message == null || message.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Message is required");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Attempt to send SMS via configured providers
            boolean sent = smsService.sendSms(phoneNumber, message);
            response.put("success", sent);
            // Return masked phone number for security
            response.put("phoneNumber", maskPhoneNumber(phoneNumber));
            response.put("message", sent ? "SMS sent successfully" : "Failed to send SMS via all providers");

            if (sent) {
                logger.info("Test SMS sent successfully to {}", maskPhoneNumber(phoneNumber));
                return ResponseEntity.ok(response);
            } else {
                // SMS providers could not deliver the message
                logger.warn("SMS delivery failed for {}", maskPhoneNumber(phoneNumber));
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            // Log exception and return error response
            logger.error("Error sending test SMS: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Mask phone number for security in responses
     * Shows only the last 4 digits with asterisks for the rest
     * 
     * @param phoneNumber - Original phone number to mask
     * @return Masked phone number (e.g., "****5678")
     */
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return "****";
        }
        // Return masked format with asterisks and last 4 digits
        return "*" + phoneNumber.substring(phoneNumber.length() - 4);
    }
}
