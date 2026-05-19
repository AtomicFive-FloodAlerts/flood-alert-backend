package Atomic5.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.User;
import Atomic5.demo.service.sms.SmsService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationService.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private SmsService smsService;

    public void sendAlertNotification(Alert alert, User recipient) {
        sendAlertEmail(alert, recipient);
        sendAlertSms(alert, recipient);
    }

    private void sendAlertEmail(Alert alert, User recipient) {

        if (mailSender == null) {
            logger.warn("Email service not configured.");
            return;
        }

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(recipient.getEmail());
            helper.setSubject("🚨 " + alert.getTitle());
            helper.setText(buildHtmlEmailContent(alert), true);

            mailSender.send(mimeMessage);

            logger.info(
                    "Alert notification email sent to {}",
                    recipient.getEmail()
            );

        } catch (MessagingException e) {

            logger.error(
                    "Failed to send alert email to {}: {}",
                    recipient.getEmail(),
                    e.getMessage()
            );
        }
    }

    private void sendAlertSms(Alert alert, User recipient) {

        if (smsService == null ||
                !smsService.isAnySmsProviderConfigured()) {

            logger.debug("SMS service not configured.");
            return;
        }

        if (recipient.getPhoneNumber() == null ||
                recipient.getPhoneNumber().isEmpty()) {

            logger.warn(
                    "User {} has no phone number",
                    recipient.getId()
            );

            return;
        }

        try {

            String smsMessage = buildSmsAlertMessage(alert);

            if (smsService.sendSms(
                    recipient.getPhoneNumber(),
                    smsMessage)) {

                logger.info(
                        "Alert SMS sent to {}",
                        recipient.getPhoneNumber()
                );

            } else {

                logger.warn(
                        "Failed to send alert SMS to {}",
                        recipient.getPhoneNumber()
                );
            }

        } catch (Exception e) {

            logger.error(
                    "Error sending SMS to {}: {}",
                    recipient.getPhoneNumber(),
                    e.getMessage()
            );
        }
    }

    private String buildHtmlEmailContent(Alert alert) {

        FloodReport report = resolveFloodReport(alert);

        if (report == null) {
            return alert.getMessage();
        }

        return String.format(
                """
                <!DOCTYPE html>
                <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2>🚨 Flood Alert</h2>

                    <p><strong>Message:</strong> %s</p>

                    <p><strong>Severity:</strong> %s</p>

                    <p><strong>Location:</strong> %s</p>

                    <p><strong>Distance:</strong> %.2f km</p>

                    <p><strong>Water Level:</strong> %d cm</p>

                    <p><strong>Description:</strong> %s</p>

                    <p><strong>Reported Time:</strong> %s</p>

                    <hr>

                    <p>Stay safe.</p>
                </body>
                </html>
                """,
                alert.getMessage(),
                report.getSeverity(),
                report.getAreaName(),
                alert.getDistanceKm(),
                report.getWaterLevel(),
                report.getDescription(),
                report.getReportTime()
        );
    }

    private String buildSmsAlertMessage(Alert alert) {

        FloodReport report = resolveFloodReport(alert);

        if (report == null) {
            return alert.getMessage();
        }

        return String.format(
                "🚨 FLOOD ALERT: %s in %s (%.1f km away). Water level: %d cm.",
                report.getSeverity(),
                report.getAreaName(),
                alert.getDistanceKm(),
                report.getWaterLevel()
        );
    }

    private FloodReport resolveFloodReport(Alert alert) {

        if (alert.getFloodReport() != null) {
            return alert.getFloodReport();
        }

        return null;
    }

    public void sendFloodReportConfirmation(
            FloodReport report,
            User reporter
    ) {

        sendFloodReportConfirmationEmail(report, reporter);

        sendFloodReportConfirmationSms(report, reporter);
    }

    private void sendFloodReportConfirmationEmail(
            FloodReport report,
            User reporter
    ) {

        if (mailSender == null) {

            logger.warn("Email service not configured.");

            return;
        }

        try {

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setTo(reporter.getEmail());

            message.setSubject(
                    "Flood Report Submitted - Confirmation"
            );

            message.setText(
                    String.format(
                            """
                            Dear %s,

                            Thank you for reporting the flood incident.

                            Area: %s
                            Water Level: %d cm
                            Severity: %s

                            Stay safe.

                            Flood Alert System
                            """,
                            reporter.getName(),
                            report.getAreaName(),
                            report.getWaterLevel(),
                            report.getSeverity()
                    )
            );

            mailSender.send(message);

            logger.info(
                    "Confirmation email sent to {}",
                    reporter.getEmail()
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to send confirmation email: {}",
                    e.getMessage()
            );
        }
    }

    private void sendFloodReportConfirmationSms(
            FloodReport report,
            User reporter
    ) {

        if (smsService == null ||
                !smsService.isAnySmsProviderConfigured()) {

            logger.debug("SMS service not configured.");

            return;
        }

        if (reporter.getPhoneNumber() == null ||
                reporter.getPhoneNumber().isEmpty()) {

            return;
        }

        try {

            String smsMessage = String.format(
                    "Flood report received for %s. Water level: %d cm.",
                    report.getAreaName(),
                    report.getWaterLevel()
            );

            smsService.sendSms(
                    reporter.getPhoneNumber(),
                    smsMessage
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to send confirmation SMS: {}",
                    e.getMessage()
            );
        }
    }
}