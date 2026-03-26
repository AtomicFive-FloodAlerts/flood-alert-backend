package Atomic5.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flood_reports")
public class FloodReport {

    @Id
    private String id;

    private Long reportedByUserId;
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

    public FloodReport(String id, Long reportedByUserId, Double latitude, Double longitude,
                       String description, FloodSeverity severity, LocalDateTime reportTime,
                       LocalDateTime expiryTime, Integer waterLevel, String areaName) {
        this.id = id;
        this.reportedByUserId = reportedByUserId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.severity = severity;
        this.reportTime = reportTime;
        this.expiryTime = expiryTime;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
    }

    public FloodReport(Long reportedByUserId, Double latitude, Double longitude,
                       String description, Integer waterLevel, String areaName) {
        this.reportedByUserId = reportedByUserId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}