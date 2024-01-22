package dev.task.dndquest.exception;

public class RaceNotFoundException extends RuntimeException {
    private static final String EX_MESSAGE = "no such race in database";

    public RaceNotFoundException() {
        super(EX_MESSAGE);
    }
}
