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
 * Provides endpoints for SMS status and testing
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired(required = false)
    private SmsService smsService;

    /**
     * Get SMS provider status
     */
    @GetMapping("/sms-status")
    public ResponseEntity<?> getSmsStatus() {
        Map<String, Object> response = new HashMap<>();

        if (smsService == null) {
            response.put("status", "SMS service not available");
            response.put("providers", new String[] {});
            response.put("configured", 0);
            return ResponseEntity.ok(response);
        }

        response.put("status", smsService.getProviderStatus());
        response.put("availableProviders", smsService.getAvailableProviders());
        response.put("isConfigured", smsService.isAnySmsProviderConfigured());

        return ResponseEntity.ok(response);
    }

    /**
     * Send test SMS
     */
    @PostMapping("/test-sms")
    public ResponseEntity<?> sendTestSms(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        if (smsService == null) {
            response.put("success", false);
            response.put("message", "SMS service not available");
            return ResponseEntity.badRequest().body(response);
        }

        if (!smsService.isAnySmsProviderConfigured()) {
            response.put("success", false);
            response.put("message", "No SMS providers configured");
            return ResponseEntity.badRequest().body(response);
        }

        String phoneNumber = request.get("phoneNumber");
        String message = request.get("message");

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Phone number is required");
            return ResponseEntity.badRequest().body(response);
        }

        if (message == null || message.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Message is required");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            boolean sent = smsService.sendSms(phoneNumber, message);
            response.put("success", sent);
            response.put("phoneNumber", maskPhoneNumber(phoneNumber));
            response.put("message", sent ? "SMS sent successfully" : "Failed to send SMS via all providers");

            if (sent) {
                logger.info("Test SMS sent successfully to {}", maskPhoneNumber(phoneNumber));
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error sending test SMS: {}", e.getMessage());
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Mask phone number for response (show only last 4 digits)
     */
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return "****";
        }
        return "*" + phoneNumber.substring(phoneNumber.length() - 4);
    }
}
