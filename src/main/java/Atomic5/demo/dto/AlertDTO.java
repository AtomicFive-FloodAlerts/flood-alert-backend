package Atomic5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertDTO {
    private Long id;
    private Long floodReportId;
    private Long recipientId;
    private String title;
    private String message;
    private String status;
    private String createdAt;
    private String readAt;
    private Double distanceKm;
    private String areaName;
    private String floodSeverity;
}
