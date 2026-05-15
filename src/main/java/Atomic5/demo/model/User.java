package Atomic5.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    private Double latitude;
    private Double longitude;

    private Boolean notificationsEnabled;

    public User(String name, String email, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.notificationsEnabled = true;
    }
}
