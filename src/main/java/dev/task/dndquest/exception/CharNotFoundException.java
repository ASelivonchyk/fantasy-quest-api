package dev.task.dndquest.exception;

public class CharNotFoundException extends RuntimeException{
   private static final String EX_MESSAGE = "no such character in database";

    public CharNotFoundException() {
        super(EX_MESSAGE);
    }
}
