package Atomic5.demo.observer;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;

public interface AlertObserver {
    void onAlert(Alert alert, User recipient);
}