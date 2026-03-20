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

        if (report == null || report.getWaterLevel() == null) {
            return 0;
        }

        int waterLevel = report.getWaterLevel();
        int baseScore = Math.min((waterLevel / 3) + 10, 100);

        FloodSeverity severity = report.getSeverity();

        if (severity == null) {
            return baseScore;
        }

        switch (severity) {
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
