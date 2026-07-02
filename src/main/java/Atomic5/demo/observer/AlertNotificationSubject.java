package Atomic5.demo.observer;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertNotificationSubject {

    private final List<AlertObserver> observers;

    public AlertNotificationSubject(AlertObserverFactory alertObserverFactory) {
        this.observers = alertObserverFactory.createObservers();
    }

    public void notifyObservers(Alert alert, User recipient) {
        for (AlertObserver observer : observers) {
            observer.onAlert(alert, recipient);
        }
    }
}