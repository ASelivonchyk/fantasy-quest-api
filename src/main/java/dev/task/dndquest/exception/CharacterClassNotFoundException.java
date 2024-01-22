package dev.task.dndquest.exception;

public class CharacterClassNotFoundException extends RuntimeException {
    private static final String EX_MESSAGE = "no such class in database";

    public CharacterClassNotFoundException() {
        super(EX_MESSAGE);
    }
}
