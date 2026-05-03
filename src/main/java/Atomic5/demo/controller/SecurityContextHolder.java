package Atomic5.demo.controller;

import javax.naming.AuthenticationException;

public class SecurityContextHolder {

    public static Object getContext() {
        throw new UnsupportedOperationException("Unimplemented method 'getContext'");
    }

    public AuthenticationException getAuthentication() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthentication'");
    }

}
