package Atomic5.demo.service;

import Atomic5.demo.dto.AuthResponse;
import Atomic5.demo.dto.LoginRequest;
import Atomic5.demo.dto.RegisterRequest;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    public String register(RegisterRequest request) {

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return "Invalid registration data";
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN
    public AuthResponse login(LoginRequest request) {

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return null;
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId(), user.getEmail());
    }
}