package Atomic5.demo.model;

public enum FloodSeverity {
    LOW(0, 30, "Low water level - Minor flooding"),
    MODERATE(31, 100, "Moderate water level - Moderate flooding"),
    HIGH(101, 200, "High water level - Severe flooding"),
    CRITICAL(201, Integer.MAX_VALUE, "Critical water level - Extreme flooding");

    private final String description;

    FloodSeverity(int minLevel, int maxLevel, String description) {
        this.description = description;
    }

    public static FloodSeverity calculateSeverity(int waterLevel) {
        if (waterLevel < 30)
            return LOW;
        if (waterLevel < 100)
            return MODERATE;
        if (waterLevel < 200)
            return HIGH;
        return CRITICAL;
    }

    public String getDescription() {
        return description;
    }
}
