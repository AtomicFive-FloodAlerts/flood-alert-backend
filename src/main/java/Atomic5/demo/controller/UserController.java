package Atomic5.demo.controller;

import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new RuntimeException("Email already registered");
        }

        return userRepository.save(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user){

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isEmpty()){
            return "User not found";
        }

        if(existingUser.get().getPassword().equals(user.getPassword())){
            return JwtUtil.generateToken(user.getEmail());
        }

        return "Invalid password";
    }

}