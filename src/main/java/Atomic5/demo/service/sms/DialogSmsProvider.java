package Atomic5.demo.service.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Dialog Axiata SMS Provider Implementation
 * Local Sri Lankan SMS service provider
 * 
 * Configuration Required in application.properties:
 * dialog.api-url=https://api.dialog.lk/sms/send
 * dialog.api-key=your_api_key
 * dialog.sender-id=your_sender_id
 * dialog.enabled=true
 * 
 * Documentation: https://www.dialog.lk/api
 */
@Component
public class DialogSmsProvider implements SmsProvider {

    private static final Logger logger = LoggerFactory.getLogger(DialogSmsProvider.class);

    @Value("${dialog.api-url:}")
    private String apiUrl;

    @Value("${dialog.api-key:}")
    private String apiKey;

    @Value("${dialog.sender-id:}")
    private String senderId;

    @Value("${dialog.enabled:false}")
    private boolean enabled;

    /**
     * Send SMS via Dialog API
     * @param phoneNumber Recipient phone number (format: +94XXXXXXXXX or 07XXXXXXXX)
     * @param message SMS message content
     * @return true if sent successfully
     */
    @Override
    public boolean sendSms(String phoneNumber, String message) {
        if (!isConfigured()) {
            logger.warn("Dialog SMS provider is not configured. SMS not sent to {}", phoneNumber);
            return false;
        }

        try {
            // Convert phone number to Dialog format if needed
            String formattedNumber = formatPhoneNumber(phoneNumber);

            // Build Dialog API request URL
            String requestUrl = String.format(
                    "%s?key=%s&to=%s&text=%s&sender=%s",
                    apiUrl,
                    urlEncode(apiKey),
                    formattedNumber,
                    urlEncode(message),
                    urlEncode(senderId)
            );

            // Send HTTP GET request using RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            try {
                String response = restTemplate.getForObject(requestUrl, String.class);
                if (response != null && response.contains("success")) {
                    logger.info("SMS sent via Dialog to {} - Response: {}", phoneNumber, response);
                    return true;
                } else {
                    logger.warn("Dialog SMS failed for {}: Response: {}", phoneNumber, response);
                    return false;
                }
            } catch (RestClientException e) {
                logger.warn("Dialog SMS failed for {}: {}", phoneNumber, e.getMessage());
                return false;
            }

        } catch (Exception e) {
            logger.error("Failed to send SMS via Dialog to {}: {}", phoneNumber, e.getMessage());
            return false;
        }
    }

    /**
     * Format phone number for Dialog API
     * Accepts: +94771234567, 0771234567
     * Returns: 94771234567
     */
    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("+")) {
            return phoneNumber.substring(1);
        } else if (phoneNumber.startsWith("0")) {
            return "94" + phoneNumber.substring(1);
        }
        return phoneNumber;
    }

    /**
     * URL encode string
     */
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            logger.warn("URL encoding failed: {}", e.getMessage());
            return value;
        }
    }

    /**
     * Check if Dialog is configured
     */
    @Override
    public boolean isConfigured() {
        return enabled && 
               apiUrl != null && !apiUrl.isEmpty() &&
               apiKey != null && !apiKey.isEmpty() &&
               senderId != null && !senderId.isEmpty();
    }

    /**
     * Get provider name
     */
    @Override
    public String getProviderName() {
        return "Dialog Axiata";
    }
}
