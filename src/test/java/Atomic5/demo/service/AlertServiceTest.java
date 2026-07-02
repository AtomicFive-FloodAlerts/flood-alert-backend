package Atomic5.demo.service;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import Atomic5.demo.model.User;
import Atomic5.demo.observer.AlertNotificationSubject;
import Atomic5.demo.repository.AlertRepository;
import Atomic5.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FloodSeverityService floodSeverityService;

    @Mock
    private AlertNotificationSubject alertNotificationSubject;

    @InjectMocks
    private AlertService alertService;

    @Test
    void generatesAlertsForNearbyUsersAndNotifiesThem() {
        User reporter = user(1L, 6.9271, 79.8612, true);
        User nearbyUser = user(2L, 6.9300, 79.8600, true);
        User disabledUser = user(3L, 6.9300, 79.8600, false);
        User farUser = user(4L, 7.2000, 80.0000, true);

        FloodReport report = floodReport(reporter, 6.9271, 79.8612, FloodSeverity.HIGH);

        when(userRepository.findAll()).thenReturn(List.of(reporter, nearbyUser, disabledUser, farUser));
        when(floodSeverityService.getAlertRadiusKm(FloodSeverity.HIGH)).thenReturn(10.0);
        when(alertRepository.existsByRecipientAndFloodReport(any(User.class), any(FloodReport.class)))
                .thenReturn(false);
        when(alertRepository.save(any(Alert.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Alert> alerts = alertService.generateAlertsForFloodReport(report);

        assertEquals(1, alerts.size());
        assertEquals(nearbyUser.getId(), alerts.get(0).getRecipient().getId());
        assertEquals(FloodSeverity.HIGH, alerts.get(0).getFloodReport().getSeverity());
        assertTrue(alerts.get(0).getDistanceKm() > 0);

        verify(alertNotificationSubject).notifyObservers(eq(alerts.get(0)), eq(nearbyUser));
    }

    @Test
    void skipsDuplicateAlertsForSameReportAndUser() {
        User reporter = user(1L, 6.9271, 79.8612, true);
        User nearbyUser = user(2L, 6.9300, 79.8600, true);

        FloodReport report = floodReport(reporter, 6.9271, 79.8612, FloodSeverity.MODERATE);

        when(userRepository.findAll()).thenReturn(List.of(reporter, nearbyUser));
        when(floodSeverityService.getAlertRadiusKm(FloodSeverity.MODERATE)).thenReturn(5.0);
        when(alertRepository.existsByRecipientAndFloodReport(nearbyUser, report)).thenReturn(true);

        List<Alert> alerts = alertService.generateAlertsForFloodReport(report);

        assertTrue(alerts.isEmpty());
        verify(alertRepository, never()).save(any(Alert.class));
        verify(alertNotificationSubject, never()).notifyObservers(any(), any(User.class));
    }

    private User user(Long id, double latitude, double longitude, boolean notificationsEnabled) {
        User user = new User();
        user.setId(id);
        user.setName("User " + id);
        user.setEmail("user" + id + "@example.com");
        user.setPassword("password");
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        user.setNotificationsEnabled(notificationsEnabled);
        return user;
    }

    private FloodReport floodReport(User reporter, double latitude, double longitude, FloodSeverity severity) {
        FloodReport report = new FloodReport();
        report.setReportedBy(reporter);
        report.setLatitude(latitude);
        report.setLongitude(longitude);
        report.setSeverity(severity);
        report.setWaterLevel(180);
        report.setAreaName("Colombo");
        report.setDescription("Rising water near the bridge");
        return report;
    }
}