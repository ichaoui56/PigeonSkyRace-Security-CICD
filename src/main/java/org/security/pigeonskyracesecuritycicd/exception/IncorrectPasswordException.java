package org.security.pigeonskyracesecuritycicd.exception;


import org.springframework.security.core.AuthenticationException;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
