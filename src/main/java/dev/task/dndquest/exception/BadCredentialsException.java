package dev.task.dndquest.exception;

public class BadCredentialsException extends RuntimeException{
    private static final String EX_MESSAGE = "login or password incorrect";

    public BadCredentialsException() {
        super(EX_MESSAGE);
    }
}
