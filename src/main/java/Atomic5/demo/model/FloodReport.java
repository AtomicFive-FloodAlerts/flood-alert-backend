package Atomic5.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "flood_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    
    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;
    
    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;
    
    private Integer waterLevel; // in cm
    private String areaName;
    
    public FloodReport(User reportedBy, Double latitude, Double longitude, 
                       String description, Integer waterLevel, String areaName) {
        this.reportedBy = reportedBy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6); // Expires after 6 hours
    }
}

enum FloodSeverity {
    LOW(0, 30, "Low water level - Minor flooding"),
    MODERATE(31, 100, "Moderate water level - Moderate flooding"),
    HIGH(101, 200, "High water level - Severe flooding"),
    CRITICAL(201, Integer.MAX_VALUE, "Critical water level - Extreme flooding");
    
    private final int minLevel;
    private final int maxLevel;
    private final String description;
    
    FloodSeverity(int minLevel, int maxLevel, String description) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.description = description;
    }
    
    public static FloodSeverity calculateSeverity(int waterLevel) {
        if (waterLevel < 30) return LOW;
        if (waterLevel < 100) return MODERATE;
        if (waterLevel < 200) return HIGH;
        return CRITICAL;
    }
    
    public String getDescription() {
        return description;
    }
}
