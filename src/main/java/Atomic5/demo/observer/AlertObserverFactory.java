package Atomic5.demo.observer;

import Atomic5.demo.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlertObserverFactory {

    private final NotificationService notificationService;

    public AlertObserverFactory(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<AlertObserver> createObservers() {
        List<AlertObserver> observers = new ArrayList<>();

        if (notificationService.isEmailConfigured()) {
            observers.add(new EmailAlertObserver(notificationService));
        }

        if (notificationService.isSmsConfigured()) {
            observers.add(new SmsAlertObserver(notificationService));
        }

        return observers;
    }
}