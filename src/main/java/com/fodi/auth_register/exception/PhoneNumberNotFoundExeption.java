package com.fodi.auth_register.exception;

import org.springframework.security.core.AuthenticationException;

public class PhoneNumberNotFoundExeption extends AuthenticationException {
    private static final long serialVersionUID = 1410688585992297006L;

    public PhoneNumberNotFoundExeption(String message) {
        super(message);
    }

    public PhoneNumberNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
