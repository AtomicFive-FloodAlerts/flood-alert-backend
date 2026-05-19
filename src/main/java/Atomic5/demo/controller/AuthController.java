package Atomic5.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Atomic5.demo.dto.LoginRequest;
import Atomic5.demo.dto.RegisterRequest;
import Atomic5.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        String result = authService.register(request);

        if ("Email already registered".equals(result) ||
            "Invalid registration data".equals(result)) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Object response = authService.login(request);

        if (response instanceof String msg) {

            return switch (msg) {

                case "User not found" ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(msg);

                case "Wrong password" ->
                        ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(msg);

                default ->
                        ResponseEntity
                                .badRequest()
                                .body(msg);
            };
        }

        return ResponseEntity.ok(response);
    }
}