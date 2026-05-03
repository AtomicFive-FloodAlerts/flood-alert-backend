package Atomic5.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches(String password, String passwordHash) {
        return bCryptPasswordEncoder.matches(password, passwordHash);
    }

}
