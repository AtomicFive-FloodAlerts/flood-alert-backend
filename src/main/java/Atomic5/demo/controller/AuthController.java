package Atomic5.demo.controller;

import Atomic5.demo.dto.AuthRequest;
import Atomic5.demo.dto.AuthResponse;
import Atomic5.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody Atomic5.demo.model.User user) {
        return service.register(user); 
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}