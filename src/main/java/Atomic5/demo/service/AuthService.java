package Atomic5.demo.service;

import Atomic5.demo.dto.AuthResponse;
import Atomic5.demo.dto.LoginRequest;
import Atomic5.demo.dto.RegisterRequest;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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

    // REGISTER — CHANGE: added phoneNumber save
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
        user.setPhoneNumber(request.getPhoneNumber()); // CHANGE: was missing
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN — unchanged
    public Object login(LoginRequest request) {

        if (request == null ||
                request.getEmail() == null ||
                request.getPassword() == null) {
            return "Invalid login data";
        }

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) return "User not found";

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            return "Wrong password";
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId(), user.getEmail());
    }

    // CHANGE: new method — replaces the broken @GetMapping oauth-success endpoint
    // Verifies Google access token by calling Google's userinfo API, then issues our JWT
    public Object googleAuth(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return "Invalid Google token";
            }

            Scanner scanner = new Scanner((InputStream) conn.getContent());
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) sb.append(scanner.nextLine());
            scanner.close();

            String json = sb.toString();
            String email = extractField(json, "email");
            String name  = extractField(json, "name");

            if (email == null) return "Google token missing email";

            User user = userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setName(name != null ? name : "");
                newUser.setPassword(""); // Google users have no password
                return userRepository.save(newUser);
            });

            String token = jwtUtil.generateToken(user.getEmail());
            return new AuthResponse(token, user.getId(), user.getEmail());

        } catch (Exception e) {
            return "Google authentication failed";
        }
    }

    private String extractField(String json, String field) {
        String key = "\"" + field + "\":\"";
        int start = json.indexOf(key);
        if (start == -1) return null;
        start += key.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}