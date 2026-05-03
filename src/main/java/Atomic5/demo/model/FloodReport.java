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
    private FloodSeverity severity;
    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;
    private Integer waterLevel;
    private String areaName;

    public FloodReport() {
    }

    public FloodReport(User reportedBy, Double latitude, Double longitude,
            String description, Integer waterLevel, String areaName) {
        this.reportedBy = reportedBy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6);
    }

    public FloodReport(Long id, User reportedBy, Double latitude, Double longitude,
            String description, FloodSeverity severity, LocalDateTime reportTime,
            LocalDateTime expiryTime, Integer waterLevel, String areaName) {
        this.id = id;
        this.reportedBy = reportedBy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.severity = severity;
        this.reportTime = reportTime;
        this.expiryTime = expiryTime;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
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

    public Object getReportedByUserId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReportedByUserId'");
    }
}