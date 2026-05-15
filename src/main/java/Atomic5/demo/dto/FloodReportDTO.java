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
}
