package Atomic5.demo.service.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Mobitel SMS Provider Implementation
 * Local Sri Lankan SMS service provider
 * 
 * Configuration Required in application.properties:
 * mobitel.api-url=https://api.mobitel.lk/sms
 * mobitel.username=your_username
 * mobitel.password=your_password
 * mobitel.sender-id=your_sender_id
 * mobitel.enabled=true
 * 
 * Documentation: https://www.mobitel.lk/api
 */
@Component
public class MobitelSmsProvider implements SmsProvider {

    private static final Logger logger = LoggerFactory.getLogger(MobitelSmsProvider.class);

    @Value("${mobitel.api-url:}")
    private String apiUrl;

    @Value("${mobitel.username:}")
    private String username;

    @Value("${mobitel.password:}")
    private String password;

    @Value("${mobitel.sender-id:}")
    private String senderId;

    @Value("${mobitel.enabled:false}")
    private boolean enabled;

    /**
     * Send SMS via Mobitel API
     * @param phoneNumber Recipient phone number (format: +94XXXXXXXXX or 07XXXXXXXX)
     * @param message SMS message content
     * @return true if sent successfully
     */
    @Override
    public boolean sendSms(String phoneNumber, String message) {
        if (!isConfigured()) {
            logger.warn("Mobitel SMS provider is not configured. SMS not sent to {}", phoneNumber);
            return false;
        }

        try {
            // Convert phone number to Mobitel format if needed
            String formattedNumber = formatPhoneNumber(phoneNumber);

            // Build Mobitel API request JSON
            String requestBody = buildMobitelRequest(formattedNumber, message);

            // Setup HTTP headers with Basic Auth
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            headers.set("Authorization", "Basic " + encodedAuth);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // Send HTTP POST request using RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    logger.info("SMS sent via Mobitel to {} - Response: {}", phoneNumber, response.getBody());
                    return true;
                } else {
                    logger.warn("Mobitel SMS failed for {}: HTTP {} - {}", phoneNumber, 
                            response.getStatusCode(), response.getBody());
                    return false;
                }
            } catch (RestClientException e) {
                logger.warn("Mobitel SMS failed for {}: {}", phoneNumber, e.getMessage());
                return false;
            }

        } catch (Exception e) {
            logger.error("Failed to send SMS via Mobitel to {}: {}", phoneNumber, e.getMessage());
            return false;
        }
    }

    /**
     * Build Mobitel API request JSON
     */
    private String buildMobitelRequest(String phoneNumber, String message) {
        return String.format(
                "{\"destination\":\"%s\",\"text\":\"%s\",\"sender\":\"%s\"}",
                phoneNumber,
                escapeJson(message),
                senderId
        );
    }

    /**
     * Format phone number for Mobitel API
     * Accepts: +94771234567, 0771234567
     * Returns: +94771234567
     */
    private String formatPhoneNumber(String phoneNumber) {
        String cleaned = phoneNumber.replaceAll("[^0-9+]", "");
        
        if (cleaned.startsWith("+")) {
            return cleaned;
        } else if (cleaned.startsWith("0")) {
            return "+94" + cleaned.substring(1);
        } else if (cleaned.startsWith("94")) {
            return "+" + cleaned;
        }
        return "+" + cleaned;
    }

    /**
     * Escape special characters for JSON
     */
    private String escapeJson(String input) {
        return input
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Check if Mobitel is configured
     */
    @Override
    public boolean isConfigured() {
        return enabled && 
               apiUrl != null && !apiUrl.isEmpty() &&
               username != null && !username.isEmpty() &&
               password != null && !password.isEmpty() &&
               senderId != null && !senderId.isEmpty();
    }

    /**
     * Get provider name
     */
    @Override
    public String getProviderName() {
        return "Mobitel";
    }
}
