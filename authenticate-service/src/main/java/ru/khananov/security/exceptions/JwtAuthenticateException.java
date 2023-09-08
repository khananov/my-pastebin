package ru.khananov.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticateException extends AuthenticationException {
    public JwtAuthenticateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticateException(String msg) {
        super(msg);
    }
}
