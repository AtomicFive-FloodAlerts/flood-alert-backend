package Atomic5.demo.service;

import Atomic5.demo.dto.AuthResponse;
import Atomic5.demo.dto.LoginRequest;
import Atomic5.demo.dto.RegisterRequest;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return "Invalid registration data";
        }

        if (userRepository.findByEmail(request.getEmail()) != null) {
            return "Email already registered";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setNotificationsEnabled(true);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return null;
        }

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || user.getPasswordHash() == null) {
            return null;
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return null;
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getId(), user.getEmail());
    }
}
