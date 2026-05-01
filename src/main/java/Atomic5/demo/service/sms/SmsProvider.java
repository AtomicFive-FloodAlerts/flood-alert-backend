package Atomic5.demo.service.sms;

/**
 * Interface for SMS notification providers
 * Allows multiple implementations for different SMS services
 */
public interface SmsProvider {
    
    /**
     * Send SMS message to recipient
     * @param phoneNumber Phone number in E.164 format (e.g., +94771234567)
     * @param message SMS message content
     * @return true if sent successfully, false otherwise
     */
    boolean sendSms(String phoneNumber, String message);
    
    /**
     * Check if provider is configured and ready to send
     * @return true if provider is configured
     */
    boolean isConfigured();
    
    /**
     * Get provider name
     * @return Name of the SMS provider
     */
    String getProviderName();
}
