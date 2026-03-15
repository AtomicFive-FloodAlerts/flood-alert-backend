package Atomic5.demo.service;

import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import org.springframework.stereotype.Service;

/**
 * Service to calculate flood severity based on water level and other factors
 */
@Service
public class FloodSeverityService {
    
    /**
     * Calculate severity based on water level (in cm)
     */
    public FloodSeverity calculateSeverityFromWaterLevel(int waterLevel) {
        return FloodSeverity.calculateSeverity(waterLevel);
    }
    
    /**
     * Calculate a severity score (0-100) for frontend visualization
     */
    public int calculateSeverityScore(FloodReport report) {
        int baseScore = 0;
        
        // Score based on water level
        if (report.getWaterLevel() != null) {
            baseScore = Math.min((report.getWaterLevel() / 3) + 10, 100);
        }
        
        // Adjust based on severity level
        switch (report.getSeverity()) {
            case LOW:
                baseScore = Math.min(baseScore, 25);
                break;
            case MODERATE:
                baseScore = Math.min(Math.max(baseScore, 30), 50);
                break;
            case HIGH:
                baseScore = Math.min(Math.max(baseScore, 60), 85);
                break;
            case CRITICAL:
                baseScore = Math.min(Math.max(baseScore, 85), 100);
                break;
        }
        
        return baseScore;
    }
    
    /**
     * Get alert radius in km based on severity level
     * Higher severity = larger radius
     */
    public double getAlertRadiusKm(FloodSeverity severity) {
        return switch (severity) {
            case LOW -> 2.0;
            case MODERATE -> 5.0;
            case HIGH -> 10.0;
            case CRITICAL -> 15.0;
        };
    }
    
    /**
     * Check if a flood report requires immediate action
     */
    public boolean requiresImmediateAction(FloodReport report) {
        return report.getSeverity() == FloodSeverity.HIGH || 
               report.getSeverity() == FloodSeverity.CRITICAL;
    }
}
