package Atomic5.demo.dto;

public class AuthResponse {

    private String token;
    private Long userId;
    private String email;

    public AuthResponse(String token, Long userId, String email) {
        this.token = token;
        this.userId = userId;
        this.email = email;
    }

    // getters
    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }

    // setters (optional)
    public void setToken(String token) { this.token = token; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
}