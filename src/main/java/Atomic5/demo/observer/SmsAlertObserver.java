package Atomic5.demo.observer;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;
import Atomic5.demo.service.NotificationService;

public class SmsAlertObserver extends AbstractAlertObserver {

    public SmsAlertObserver(NotificationService notificationService) {
        super(notificationService);
    }

    @Override
    protected void dispatch(Alert alert, User recipient) {
        notificationService.sendAlertSms(alert, recipient);
    }
}