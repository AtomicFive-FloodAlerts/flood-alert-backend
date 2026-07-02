package Atomic5.demo.observer;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;
import Atomic5.demo.service.NotificationService;

public abstract class AbstractAlertObserver implements AlertObserver {

    protected final NotificationService notificationService;

    protected AbstractAlertObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public final void onAlert(Alert alert, User recipient) {
        dispatch(alert, recipient);
    }

    protected abstract void dispatch(Alert alert, User recipient);
}