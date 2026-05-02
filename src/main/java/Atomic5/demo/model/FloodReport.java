package Atomic5.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flood_report")
public class FloodReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reported_by_user_id", nullable = false)
    private Long reportedById;

    private Double latitude;
    private Double longitude;
    private String description;

    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;

    @Column(name = "report_time")
    private LocalDateTime reportTime;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @Column(name = "water_level")
    private Double waterLevel;

    @Column(name = "area_name")
    private String areaName;

    public FloodReport() {
    }

    public FloodReport(Long reportedById, Double latitude, Double longitude,
                       String description, Double waterLevel, String areaName) {
        this.reportedById = reportedById;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6);
    }

    public Long getId() { return id; }

    public Long getReportedById() { return reportedById; }
    public void setReportedById(Long reportedById) { this.reportedById = reportedById; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public FloodSeverity getSeverity() { return severity; }
    public void setSeverity(FloodSeverity severity) { this.severity = severity; }

    public LocalDateTime getReportTime() { return reportTime; }
    public void setReportTime(LocalDateTime reportTime) { this.reportTime = reportTime; }

    public LocalDateTime getExpiryTime() { return expiryTime; }
    public void setExpiryTime(LocalDateTime expiryTime) { this.expiryTime = expiryTime; }

    public Double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(Double waterLevel) { this.waterLevel = waterLevel; }

    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
}