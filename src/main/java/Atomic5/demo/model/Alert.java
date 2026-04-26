package Atomic5.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flood_report_id")
    private FloodReport floodReport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime readAt;

    private Double distanceKm; // Distance from user to flood location

    public Alert() {
    }

    public Alert(FloodReport floodReport, User recipient, String title, String message,
            AlertStatus status, LocalDateTime createdAt, LocalDateTime readAt, Double distanceKm) {
        this.floodReport = floodReport;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.readAt = readAt;
        this.distanceKm = distanceKm;
    }

    public Alert(Long id, FloodReport floodReport, User recipient, String title, String message,
            AlertStatus status, LocalDateTime createdAt, LocalDateTime readAt, Double distanceKm) {
        this.id = id;
        this.floodReport = floodReport;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.readAt = readAt;
        this.distanceKm = distanceKm;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = AlertStatus.UNREAD;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FloodReport getFloodReport() {
        return floodReport;
    }

    public void setFloodReport(FloodReport floodReport) {
        this.floodReport = floodReport;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
}

