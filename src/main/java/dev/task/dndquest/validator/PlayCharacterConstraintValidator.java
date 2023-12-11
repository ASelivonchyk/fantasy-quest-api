package dev.task.dndquest.validator;

import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import dev.task.dndquest.validator.annotation.PlayCharactersConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlayCharacterConstraintValidator
        implements ConstraintValidator<PlayCharactersConstraint, PlayCharacterRequestDto> {
    private static final int MIN_PLAY_CHAR_VALUE = 5;
    private static final int MAX_PLAY_CHAR_VALUE = 20;

    @Override
    public void initialize(PlayCharactersConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PlayCharacterRequestDto value,
                           ConstraintValidatorContext context) {
        return (value.getStrength() >= MIN_PLAY_CHAR_VALUE
                && value.getStrength() <= MAX_PLAY_CHAR_VALUE)
                && (value.getDexterity() >= MIN_PLAY_CHAR_VALUE
                && value.getDexterity() <= MAX_PLAY_CHAR_VALUE)
                && (value.getConstitution() >= MIN_PLAY_CHAR_VALUE
                && value.getConstitution() <= MAX_PLAY_CHAR_VALUE)
                && (value.getIntelligence() >= MIN_PLAY_CHAR_VALUE
                && value.getIntelligence() <= MAX_PLAY_CHAR_VALUE)
                && (value.getWisdom() >= MIN_PLAY_CHAR_VALUE
                && value.getWisdom() <= MAX_PLAY_CHAR_VALUE)
                && (value.getCharisma() >= MIN_PLAY_CHAR_VALUE
                && value.getCharisma() <= MAX_PLAY_CHAR_VALUE);
    }
}
