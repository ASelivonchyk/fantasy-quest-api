package dev.task.dndquest.exception;

public class ClassNotFoundException extends RuntimeException {
    private static final String EX_MESSAGE = "no such class in database";

    public ClassNotFoundException() {
        super(EX_MESSAGE);
    }
}
