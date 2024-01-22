package dev.task.dndquest.exception;

public class JwtAuthenticationException extends RuntimeException {
    private static final String EX_MESSAGE = "Expired or invalid JWT token";

    public JwtAuthenticationException() {
        super(EX_MESSAGE);
    }
}
