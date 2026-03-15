package Atomic5.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "flood_report_id")
    private FloodReport floodReport;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;
    
    private String title;
    private String message;
    
    @Enumerated(EnumType.STRING)
    private AlertStatus status;
    
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    
    private Double distanceKm; // Distance from user to flood location
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = AlertStatus.UNREAD;
        }
    }
}

enum AlertStatus {
    UNREAD("Not yet read by user"),
    READ("User has seen the alert"),
    DISMISSED("User dismissed the alert"),
    ACKNOWLEDGED("User acknowledged the alert");
    
    private final String description;
    
    AlertStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
