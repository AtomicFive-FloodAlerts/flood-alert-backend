package Atomic5.demo.dto;

public class FloodReportDTO {
    private String id;
    private Long reportedById;
    private Double latitude;
    private Double longitude;
    private String description;
    private String severity;
    private Integer waterLevel;
    private String areaName;
    private String reportTime;

    public FloodReportDTO() {
    }

    public FloodReportDTO(String id, Long reportedById, Double latitude, Double longitude,
                          String description, String severity, Integer waterLevel,
                          String areaName, String reportTime) {
        this.id = id;
        this.reportedById = reportedById;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.severity = severity;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = reportTime;
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
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public Integer getWaterLevel() { return waterLevel; }
    public void setWaterLevel(Integer waterLevel) { this.waterLevel = waterLevel; }
    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
    public String getReportTime() { return reportTime; }
    public void setReportTime(String reportTime) { this.reportTime = reportTime; }
}
