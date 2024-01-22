package dev.task.dndquest.validator;

import dev.task.dndquest.validator.annotation.PlayCharacterStatConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlayCharacterStatConstraintValidator
        implements ConstraintValidator<PlayCharacterStatConstraint, Integer> {
    private static final int MIN_PLAY_CHAR_VALUE = 5;
    private static final int MAX_PLAY_CHAR_VALUE = 20;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= MIN_PLAY_CHAR_VALUE && value <= MAX_PLAY_CHAR_VALUE;
    }
}
