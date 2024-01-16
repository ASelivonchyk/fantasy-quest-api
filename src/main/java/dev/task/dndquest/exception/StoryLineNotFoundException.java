package dev.task.dndquest.exception;

public class StoryLineNotFoundException extends RuntimeException {
    private static final String EX_MESSAGE = "no such story line";

    public StoryLineNotFoundException() {
        super(EX_MESSAGE);
    }
}
