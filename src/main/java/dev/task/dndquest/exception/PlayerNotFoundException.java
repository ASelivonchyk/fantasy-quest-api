package dev.task.dndquest.exception;

public class PlayerNotFoundException extends RuntimeException{
    private static final String EX_MESSAGE = "login or password incorrect";

    public PlayerNotFoundException() {
        super(EX_MESSAGE);
    }
}
