package Atomic5.demo.service.sms;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * SMS Service Manager
 * Handles routing between multiple SMS providers with failover capability
 * 
 * Priority order:
 * 1. Dialog Axiata (local Sri Lankan provider - recommended for SL)
 * 2. Mobitel (local Sri Lankan provider)
 * 3. Twilio (international provider - fallback)
 */
@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final List<SmsProvider> providers;

    public SmsService(DialogSmsProvider dialogProvider, 
                      MobitelSmsProvider mobitelProvider,
                      TwilioSmsProvider twilioProvider) {
        this.providers = new ArrayList<>();
        
        // Add providers in priority order
        this.providers.add(dialogProvider);
        this.providers.add(mobitelProvider);
        this.providers.add(twilioProvider);
    }

    /**
     * Send SMS with automatic failover
     * Tries providers in order until one succeeds
     * 
     * @param phoneNumber Recipient phone number (accepts multiple formats)
     * @param message SMS message content
     * @return true if sent successfully via any provider
     */
    public boolean sendSms(String phoneNumber, String message) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            logger.warn("Phone number is empty");
            return false;
        }

        if (message == null || message.trim().isEmpty()) {
            logger.warn("Message is empty");
            return false;
        }

        logger.info("Attempting to send SMS to {}", maskPhoneNumber(phoneNumber));

        // Try each provider in priority order
        for (SmsProvider provider : providers) {
            if (provider.isConfigured()) {
                try {
                    if (provider.sendSms(phoneNumber, message)) {
                        logger.info("SMS sent successfully via {}", provider.getProviderName());
                        return true;
                    }
                } catch (Exception e) {
                    logger.warn("Failed to send SMS via {}: {}", provider.getProviderName(), e.getMessage());
                    // Continue to next provider
                }
            } else {
                logger.debug("{} provider not configured", provider.getProviderName());
            }
        }

        logger.error("Failed to send SMS to {} via all configured providers", maskPhoneNumber(phoneNumber));
        return false;
    }

    /**
     * Send SMS to multiple recipients
     * @param phoneNumbers List of recipient phone numbers
     * @param message SMS message content
     * @return true if sent to at least one recipient
     */
    public boolean sendSmsToMultiple(List<String> phoneNumbers, String message) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            logger.warn("Phone number list is empty");
            return false;
        }

        int successCount = 0;
        for (String phoneNumber : phoneNumbers) {
            if (sendSms(phoneNumber, message)) {
                successCount++;
            }
        }

        logger.info("SMS sent to {}/{} recipients", successCount, phoneNumbers.size());
        return successCount > 0;
    }

    /**
     * Get status of all configured providers
     * @return String describing provider status
     */
    public String getProviderStatus() {
        StringBuilder status = new StringBuilder("SMS Provider Status:\n");
        
        for (SmsProvider provider : providers) {
            status.append(String.format(
                    "- %s: %s\n",
                    provider.getProviderName(),
                    provider.isConfigured() ? "✓ Configured" : "✗ Not Configured"
            ));
        }
        
        return status.toString();
    }

    /**
     * Get list of available providers
     */
    public List<String> getAvailableProviders() {
        List<String> available = new ArrayList<>();
        for (SmsProvider provider : providers) {
            if (provider.isConfigured()) {
                available.add(provider.getProviderName());
            }
        }
        return available;
    }

    /**
     * Check if any SMS provider is configured
     */
    public boolean isAnySmsProviderConfigured() {
        return providers.stream().anyMatch(SmsProvider::isConfigured);
    }

    /**
     * Mask phone number for logging (show only last 4 digits)
     */
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return "****";
        }
        return "*" + phoneNumber.substring(phoneNumber.length() - 4);
    }
}
