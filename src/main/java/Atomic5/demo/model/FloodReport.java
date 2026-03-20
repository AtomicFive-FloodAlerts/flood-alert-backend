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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User reportedBy;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;

    @Column(nullable = false)
    private Integer waterLevel; // in cm

    @Column(nullable = false)
    private String areaName;

    public FloodReport(User reportedBy, Double latitude, Double longitude,
            String description, Integer waterLevel, String areaName) {
        this.reportedBy = reportedBy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.waterLevel = waterLevel;
        this.areaName = areaName;
    }

    @PrePersist
    protected void onCreate() {
        this.reportTime = LocalDateTime.now();
        this.expiryTime = LocalDateTime.now().plusHours(6); // expires after 6 hours
    }
}
