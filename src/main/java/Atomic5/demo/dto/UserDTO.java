package Atomic5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Boolean notificationsEnabled;
    
    public UserDTO(String name, String email, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.notificationsEnabled = true;
    }
}
