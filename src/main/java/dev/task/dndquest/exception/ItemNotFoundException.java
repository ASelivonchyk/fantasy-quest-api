package dev.task.dndquest.exception;

public class ItemNotFoundException extends RuntimeException{
    private static final String EX_MESSAGE = "no such item in database";

    public ItemNotFoundException() {
        super(EX_MESSAGE);
    }
}
