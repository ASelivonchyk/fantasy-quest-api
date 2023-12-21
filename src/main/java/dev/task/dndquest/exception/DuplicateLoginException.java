package dev.task.dndquest.exception;

public class DuplicateLoginException extends RuntimeException {
    private static final String EX_MESSAGE = "player with such login already registered";

    public DuplicateLoginException() {
        super(EX_MESSAGE);
    }
}
