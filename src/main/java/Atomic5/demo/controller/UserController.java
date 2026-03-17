package Atomic5.demo.controller;

import Atomic5.demo.dto.UserDTO;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already registered");
        }

        User user = new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getLatitude(),
                userDTO.getLongitude()
        );

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDTO(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        if (existingUser.get().getPassword().equals(user.getPassword())) {
            String token = JwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid password");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(convertToDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/location")
    public ResponseEntity<?> updateUserLocation(
            @PathVariable Long userId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        return userRepository.findById(userId)
                .map(user -> {
                    user.setLatitude(latitude);
                    user.setLongitude(longitude);
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(convertToDTO(updatedUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/notifications")
    public ResponseEntity<?> toggleNotifications(
            @PathVariable Long userId,
            @RequestParam Boolean enabled) {

        return userRepository.findById(userId)
                .map(user -> {
                    user.setNotificationsEnabled(enabled);
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(convertToDTO(updatedUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setLatitude(user.getLatitude());
        dto.setLongitude(user.getLongitude());
        dto.setNotificationsEnabled(user.getNotificationsEnabled());
        return dto;
    }
}