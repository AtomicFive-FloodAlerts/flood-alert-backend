package Atomic5.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flood_reports")
public class FloodReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportedBy;

    private Double latitude;
    private Double longitude;
    private String description;

    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;

    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;

    private Double waterLevel; 
    private String areaName;

    public FloodReport() {}

    public FloodReport(User reportedBy, Double latitude, Double longitude,
                       String description, Double waterLevel, String areaName) {

        this.reportedBy = reportedBy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6);
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public String getAreaName() {
        return areaName;
    }

    public FloodSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(FloodSeverity severity) {
        this.severity = severity;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }
}