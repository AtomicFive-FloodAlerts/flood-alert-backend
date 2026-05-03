package Atomic5.demo.service;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.FloodReportRepository;
import Atomic5.demo.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

/**
 * Service for sending notifications to users via email and SMS
 * Supports multiple notification channels:
 * - Email (via JavaMailSender)
 * - SMS (via Twilio, Dialog Axiata, or Mobitel)
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private SmsService smsService;

    @Autowired(required = false)
    private FloodReportRepository floodReportRepository;

    /**
     * Send alert notification via email and SMS
     */
    public void sendAlertNotification(Alert alert, User recipient) {
        sendAlertEmail(alert, recipient);
        sendAlertSms(alert, recipient);
    }

    /**
     * Send alert notification email to user
     */
    private void sendAlertEmail(Alert alert, User recipient) {
        if (mailSender == null) {
            logger.warn("Email service not configured. Alert notification not sent.");
            return;
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(recipient.getEmail());
            helper.setSubject("🚨 " + alert.getTitle());
            helper.setText(buildHtmlEmailContent(alert), true);

            mailSender.send(mimeMessage);
            logger.info("Alert notification email sent to {}", recipient.getEmail());
        } catch (MessagingException e) {
            logger.error("Failed to send alert notification email to {}: {}", recipient.getEmail(), e.getMessage());
        }
    }

    /**
     * Send alert notification SMS to user
     */
    private void sendAlertSms(Alert alert, User recipient) {
        if (smsService == null || !smsService.isAnySmsProviderConfigured()) {
            logger.debug("SMS service not configured. Alert SMS not sent.");
            return;
        }

        if (recipient.getPhoneNumber() == null || recipient.getPhoneNumber().isEmpty()) {
            logger.warn("User {} has no phone number for SMS notification", recipient.getId());
            return;
        }

        try {
            String smsMessage = buildSmsAlertMessage(alert);
            if (smsService.sendSms(recipient.getPhoneNumber(), smsMessage)) {
                logger.info("Alert notification SMS sent to {}", recipient.getPhoneNumber());
            } else {
                logger.warn("Failed to send alert SMS to {}", recipient.getPhoneNumber());
            }
        } catch (Exception e) {
            logger.error("Error sending alert SMS to {}: {}", recipient.getPhoneNumber(), e.getMessage());
        }
    }

    /**
     * Build HTML email content for alert
     */
    private String buildHtmlEmailContent(Alert alert) {
        if (alert.getFloodReportId() == null || floodReportRepository == null) {
            return alert.getMessage();
        }
        Optional<FloodReport> reportOpt = floodReportRepository.findById(alert.getFloodReportId());
        if (reportOpt.isEmpty()) {
            return alert.getMessage();
        }
        FloodReport report = reportOpt.get();

        return String.format(
                """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <style>
                                body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                                .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                                .header { background-color: #dc3545; color: white; padding: 20px; border-radius: 5px; text-align: center; }
                                .header h1 { margin: 0; font-size: 24px; }
                                .content { background-color: #f9f9f9; padding: 20px; margin-top: 10px; border-radius: 5px; }
                                .detail-row { margin: 10px 0; padding: 10px; background-color: white; border-left: 4px solid #dc3545; }
                                .detail-label { font-weight: bold; color: #dc3545; }
                                .severity { padding: 5px 10px; border-radius: 3px; color: white; display: inline-block; }
                                .severity-critical { background-color: #8b0000; }
                                .severity-high { background-color: #dc3545; }
                                .severity-moderate { background-color: #ff9800; }
                                .severity-low { background-color: #ffc107; color: #333; }
                                .footer { text-align: center; margin-top: 20px; font-size: 12px; color: #999; }
                                .actions { margin-top: 20px; text-align: center; }
                                .action-btn { display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 3px; margin: 0 5px; }
                            </style>
                        </head>
                        <body>
                            <div class="container">
                                <div class="header">
                                    <h1>🚨 %s Alert</h1>
                                </div>

                                <div class="content">
                                    <p><strong>Alert Message:</strong></p>
                                    <p>%s</p>

                                    <div class="detail-row">
                                        <span class="detail-label">Flood Severity:</span>
                                        <span class="severity severity-%s">%s</span>
                                    </div>

                                    <div class="detail-row">
                                        <span class="detail-label">Location:</span> %s
                                    </div>

                                    <div class="detail-row">
                                        <span class="detail-label">Distance from you:</span> %.2f km
                                    </div>

                                    <div class="detail-row">
                                        <span class="detail-label">Water Level:</span> %d cm
                                    </div>

                                    <div class="detail-row">
                                        <span class="detail-label">Description:</span> %s
                                    </div>

                                    <div class="detail-row">
                                        <span class="detail-label">Reported Time:</span> %s
                                    </div>

                                    <p style="color: #666; font-size: 14px; margin-top: 20px;">
                                        <strong>Safety Tips:</strong>
                                        <ul>
                                            <li>Move to higher ground immediately</li>
                                            <li>Avoid driving through flooded areas</li>
                                            <li>Stay informed through emergency services</li>
                                            <li>Keep emergency contacts handy</li>
                                        </ul>
                                    </p>
                                </div>

                                <div class="footer">
                                    <p>This is an automated alert from the Flood Alert System.</p>
                                    <p>Please take necessary precautions and stay safe.</p>
                                </div>
                            </div>
                        </body>
                        </html>
                        """,
                report.getSeverity(),
                alert.getMessage(),
                report.getSeverity().name().toLowerCase(),
                report.getSeverity(),
                report.getAreaName(),
                alert.getDistanceKm(),
                report.getWaterLevel(),
                report.getDescription(),
                report.getReportTime());
    }

    /**
     * Build SMS alert message
     */
    private String buildSmsAlertMessage(Alert alert) {
        if (alert.getFloodReportId() == null || floodReportRepository == null) {
            return alert.getMessage();
        }
        Optional<FloodReport> reportOpt = floodReportRepository.findById(alert.getFloodReportId());
        if (reportOpt.isEmpty()) {
            return alert.getMessage();
        }
        FloodReport report = reportOpt.get();

        return String.format(
                "🚨 FLOOD ALERT: %s in %s (Distance: %.1f km). Water level: %d cm. %s",
                report.getSeverity(),
                report.getAreaName(),
                alert.getDistanceKm(),
                report.getWaterLevel(),
                "Stay safe!");
    }

    /**
     * Send flood report confirmation via email and SMS
     */
    public void sendFloodReportConfirmation(FloodReport report, User reporter) {
        sendFloodReportConfirmationEmail(report, reporter);
        sendFloodReportConfirmationSms(report, reporter);
    }

    /**
     * Send flood report confirmation email
     */
    private void sendFloodReportConfirmationEmail(FloodReport report, User reporter) {
        if (mailSender == null) {
            logger.warn("Email service not configured. Report confirmation email not sent.");
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(reporter.getEmail());
            message.setSubject("Flood Report Submitted - Confirmation");
            message.setText(String.format(
                    "Dear %s,\n\n" +
                            "Thank you for reporting the flood incident at %s.\n\n" +
                            "Report Details:\n" +
                            "- Location: (%.4f, %.4f)\n" +
                            "- Water Level: %d cm\n" +
                            "- Severity: %s\n" +
                            "- Description: %s\n\n" +
                            "Alerts have been generated and sent to nearby users.\n\n" +
                            "Stay safe,\nFlood Alert System",
                    reporter.getName(),
                    report.getAreaName(),
                    report.getLatitude(),
                    report.getLongitude(),
                    report.getWaterLevel(),
                    report.getSeverity(),
                    report.getDescription()));

            mailSender.send(message);
            logger.info("Flood report confirmation email sent to {}", reporter.getEmail());
        } catch (Exception e) {
            logger.error("Failed to send report confirmation email to {}: {}", reporter.getEmail(), e.getMessage());
        }
    }

    /**
     * Send flood report confirmation SMS
     */
    private void sendFloodReportConfirmationSms(FloodReport report, User reporter) {
        if (smsService == null || !smsService.isAnySmsProviderConfigured()) {
            logger.debug("SMS service not configured. Report confirmation SMS not sent.");
            return;
        }

        if (reporter.getPhoneNumber() == null || reporter.getPhoneNumber().isEmpty()) {
            logger.debug("Reporter {} has no phone number for SMS confirmation", reporter.getId());
            return;
        }

        try {
            String smsMessage = String.format(
                    "Flood report received for %s (Level: %d cm, Severity: %s). Alerts sent to nearby users. Stay safe!",
                    report.getAreaName(),
                    report.getWaterLevel(),
                    report.getSeverity());

            if (smsService.sendSms(reporter.getPhoneNumber(), smsMessage)) {
                logger.info("Flood report confirmation SMS sent to {}", reporter.getPhoneNumber());
            } else {
                logger.warn("Failed to send report confirmation SMS to {}", reporter.getPhoneNumber());
            }
        } catch (Exception e) {
            logger.error("Error sending report confirmation SMS to {}: {}", reporter.getPhoneNumber(), e.getMessage());
        }
    }
}
