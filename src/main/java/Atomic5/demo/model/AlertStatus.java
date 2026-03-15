package Atomic5.demo.model;

public enum AlertStatus {
    UNREAD("Not yet read by user"),
    READ("User has seen the alert"),
    DISMISSED("User dismissed the alert"),
    ACKNOWLEDGED("User acknowledged the alert");

    private final String description;

    AlertStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
