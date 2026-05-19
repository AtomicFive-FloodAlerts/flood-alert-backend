package Atomic5.demo.dto;

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

    public AlertDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFloodReportId() {
        return floodReportId;
    }

    public void setFloodReportId(Long floodReportId) {
        this.floodReportId = floodReportId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReadAt() {
        return readAt;
    }

    public void setReadAt(String readAt) {
        this.readAt = readAt;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getFloodSeverity() {
        return floodSeverity;
    }

    public void setFloodSeverity(String floodSeverity) {
        this.floodSeverity = floodSeverity;
    }
}