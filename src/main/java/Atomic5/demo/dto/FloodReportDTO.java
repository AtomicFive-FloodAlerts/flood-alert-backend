package Atomic5.demo.dto;

public class FloodReportDTO {

    private Long reportedById;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double waterLevel;
    private String areaName;

    public Long getReportedById() { return reportedById; }
    public void setReportedById(Long reportedById) { this.reportedById = reportedById; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(Double waterLevel) { this.waterLevel = waterLevel; }

    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
}