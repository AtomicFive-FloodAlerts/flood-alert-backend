package Atomic5.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flood_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}