package Atomic5.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {}

    // getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}