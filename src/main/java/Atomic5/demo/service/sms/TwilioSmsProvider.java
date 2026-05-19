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
import java.net.URLEncoder;

/**
 * Twilio SMS Provider Implementation
 * Supports SMS delivery to Sri Lanka and worldwide
 * 
 * Configuration Required in application.properties:
 * twilio.account-sid=your_account_sid
 * twilio.auth-token=your_auth_token
 * twilio.phone-number=your_twilio_phone_number
 * twilio.enabled=true
 */
@Component
public class TwilioSmsProvider implements SmsProvider {

    private static final Logger logger = LoggerFactory.getLogger(TwilioSmsProvider.class);
    private static final String TWILIO_API_URL = "https://api.twilio.com/2010-04-01/Accounts/{accountSid}/Messages";

    @Value("${twilio.account-sid:}")
    private String accountSid;

    @Value("${twilio.auth-token:}")
    private String authToken;

    @Value("${twilio.phone-number:}")
    private String fromPhoneNumber;

    @Value("${twilio.enabled:false}")
    private boolean enabled;

    /**
     * Send SMS via Twilio
     * @param phoneNumber Recipient phone number in E.164 format
     * @param message SMS message content
     * @return true if sent successfully
     */
    @Override
    public boolean sendSms(String phoneNumber, String message) {
        if (!isConfigured()) {
            logger.warn("Twilio is not configured. SMS not sent to {}", phoneNumber);
            return false;
        }

        try {
            // Validate phone number format
            if (!phoneNumber.startsWith("+")) {
                logger.warn("Phone number must be in E.164 format (+xxx...). Got: {}", phoneNumber);
                return false;
            }

            // Build Twilio API URL
            String url = TWILIO_API_URL.replace("{accountSid}", accountSid);

            // Build form data for Twilio
            String formData = String.format(
                    "To=%s&From=%s&Body=%s",
                    urlEncode(phoneNumber),
                    urlEncode(fromPhoneNumber),
                    urlEncode(message)
            );

            // Setup HTTP headers with Basic Auth
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = accountSid + ":" + authToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            headers.set("Authorization", "Basic " + encodedAuth);

            HttpEntity<String> entity = new HttpEntity<>(formData, headers);

            // Send HTTP POST request using RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                if (response.getStatusCode().is2xxSuccessful() && 
                    response.getBody() != null && response.getBody().contains("sid")) {
                    logger.info("SMS sent via Twilio to {}", phoneNumber);
                    return true;
                } else {
                    logger.warn("Twilio SMS failed for {}: HTTP {} - {}", phoneNumber, 
                            response.getStatusCode(), response.getBody());
                    return false;
                }
            } catch (RestClientException e) {
                logger.warn("Twilio SMS failed for {}: {}", phoneNumber, e.getMessage());
                return false;
            }

        } catch (Exception e) {
            logger.error("Failed to send SMS via Twilio to {}: {}", phoneNumber, e.getMessage());
            return false;
        }
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
     * Check if Twilio is configured
     */
    @Override
    public boolean isConfigured() {
        return enabled && 
               accountSid != null && !accountSid.isEmpty() &&
               authToken != null && !authToken.isEmpty() &&
               fromPhoneNumber != null && !fromPhoneNumber.isEmpty();
    }

    /**
     * Get provider name
     */
    @Override
    public String getProviderName() {
        return "Twilio";
    }
}
