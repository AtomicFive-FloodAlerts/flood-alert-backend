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
    public FloodSeverity calculateSeverityFromWaterLevel(Double waterLevel) {

        if (waterLevel == null) return FloodSeverity.LOW;

        if (waterLevel < 50) {
            return FloodSeverity.LOW;
        } else if (waterLevel < 100) {
            return FloodSeverity.MODERATE;
        } else if (waterLevel < 200) {
            return FloodSeverity.HIGH;
        } else {
            return FloodSeverity.CRITICAL;
        }
    }

    /**
     * Calculate a severity score (0-100) for frontend visualization
     */
    public int calculateSeverityScore(FloodReport report) {

        int baseScore = 0;

        if (report.getWaterLevel() != null) {
            baseScore = Math.min((int)(report.getWaterLevel() / 3) + 10, 100);
        }

        switch (report.getSeverity()) {
            case LOW:
                return Math.min(baseScore, 25);
            case MODERATE:
                return Math.min(Math.max(baseScore, 30), 50);
            case HIGH:
                return Math.min(Math.max(baseScore, 60), 85);
            case CRITICAL:
                return Math.min(Math.max(baseScore, 85), 100);
            default:
                return baseScore;
        }
    }


    /**
     * Get alert radius in km based on severity level
     */
    public double getAlertRadiusKm(FloodSeverity severity) {
        switch (severity) {
            case LOW:
                return 2.0;
            case MODERATE:
                return 5.0;
            case HIGH:
                return 10.0;
            case CRITICAL:
                return 15.0;
            default:
                return 2.0;
        }
    }

    /**
     * Check if a flood report requires immediate action
     */
    public boolean requiresImmediateAction(FloodReport report) {
        return report.getSeverity() == FloodSeverity.HIGH ||
               report.getSeverity() == FloodSeverity.CRITICAL;
    }
}