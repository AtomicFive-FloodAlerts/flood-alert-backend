package Atomic5.demo.observer;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;
import Atomic5.demo.service.NotificationService;

public class EmailAlertObserver extends AbstractAlertObserver {

    public EmailAlertObserver(NotificationService notificationService) {
        super(notificationService);
    }

    @Override
    protected void dispatch(Alert alert, User recipient) {
        notificationService.sendAlertEmail(alert, recipient);
    }
}