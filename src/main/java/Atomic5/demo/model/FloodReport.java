package Atomic5.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FloodReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reportedByUserId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reportTime;

    private LocalDateTime expiryTime;

    @Column(nullable = false)
    private Integer waterLevel;

    @Column(nullable = false)
    private String areaName;

    public FloodReport() {}

    public FloodReport(Long reportedByUserId, Double latitude, Double longitude,
                       String description, Integer waterLevel, String areaName) {
        this.reportedByUserId = reportedByUserId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
    }

    @PrePersist
    protected void onCreate() {
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6);
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public Long getReportedByUserId() {
        return reportedByUserId;
    }

    public void setReportedByUserId(Long reportedByUserId) {
        this.reportedByUserId = reportedByUserId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Integer waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}