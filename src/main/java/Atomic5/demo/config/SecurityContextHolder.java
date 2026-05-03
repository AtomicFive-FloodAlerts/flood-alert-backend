package Atomic5.demo.config;

public class SecurityContextHolder {

    public void setAuthentication(UsernamePasswordAuthenticationToken authentication) {
        throw new UnsupportedOperationException("Unimplemented method 'setAuthentication'");
    }

    public static SecurityContextHolder getContext() {
        throw new UnsupportedOperationException("Unimplemented method 'getContext'");
    }

}
