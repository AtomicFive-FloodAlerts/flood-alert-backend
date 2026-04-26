package Atomic5.demo.controller;

import Atomic5.demo.dto.AuthRequest;
import Atomic5.demo.dto.AuthResponse;
import Atomic5.demo.model.User;
import Atomic5.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return service.login(request);
    }
}