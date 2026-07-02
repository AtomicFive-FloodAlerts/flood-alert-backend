package Atomic5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloodReportDTO {
    private Long id;
    private Long reportedById;
    private Double latitude;
    private Double longitude;
    private String description;
    private String severity;
    private Integer waterLevel;
    private String areaName;
    private String reportTime;

    public Long getId() { return id; }
    public Long getReportedById() { return reportedById; }
    public Double getLatitude() { return latitude; } 
    public Double getLongitude() { return longitude; }
    public String getDescription() { return description; }  
    public String getSeverity() { return severity; }
    public Integer getWaterLevel() { return waterLevel; }
    public String getAreaName() { return areaName; }
    public String getReportTime() { return reportTime; } 
}
