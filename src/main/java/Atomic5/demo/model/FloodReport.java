package Atomic5.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flood_reports")
public class FloodReport {

    @Id
    private String id;

    private Long reportedById;
    private Double latitude;
    private Double longitude;
    private String description;
    private FloodSeverity severity;
    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;
    private Double waterLevel;
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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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