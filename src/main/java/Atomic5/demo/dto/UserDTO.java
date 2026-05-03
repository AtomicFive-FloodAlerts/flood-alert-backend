package Atomic5.demo.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Boolean notificationsEnabled;

    public UserDTO() {
    }

    public UserDTO(String name, String email, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserDTO(Long id, String name, String email, String phoneNumber, Double latitude, Double longitude,
            Boolean notificationsEnabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.notificationsEnabled = notificationsEnabled;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
}
