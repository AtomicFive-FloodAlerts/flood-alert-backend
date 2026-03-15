package Atomic5.demo.util;

/**
 * Utility class for location-based calculations using Haversine formula
 */
public class LocationUtil {
    
    private static final double EARTH_RADIUS_KM = 6371.0;
    
    /**
     * Calculate distance between two geographical points using Haversine formula
     * @param lat1 Latitude of point 1
     * @param lon1 Longitude of point 1
     * @param lat2 Latitude of point 2
     * @param lon2 Longitude of point 2
     * @return Distance in kilometers
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS_KM * c;
    }
    
    /**
     * Check if a user is within the alert radius from a flood location
     * @param userLat User latitude
     * @param userLon User longitude
     * @param floodLat Flood location latitude
     * @param floodLon Flood location longitude
     * @param radiusKm Alert radius in kilometers
     * @return true if user is within the radius
     */
    public static boolean isWithinRadius(double userLat, double userLon, 
                                         double floodLat, double floodLon, double radiusKm) {
        double distance = calculateDistance(userLat, userLon, floodLat, floodLon);
        return distance <= radiusKm;
    }
}
