package dev.task.dndquest.validator;

import dev.task.dndquest.model.dto.PlayCharacterRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CharacterClassConstraintValidator
         implements ConstraintValidator<CharacterClassConstraint, PlayCharacterRequestDto> {
    @Override
    public void initialize(CharacterClassConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(PlayCharacterRequestDto value,
                           ConstraintValidatorContext context) {
       // return Arrays.stream(PlayCharacterClassName.values())
       //         .forEach(System.out::println);
        return true;
                //.anyMatch(c -> c.equals(value.getPlayCharClass()));
    }
}
