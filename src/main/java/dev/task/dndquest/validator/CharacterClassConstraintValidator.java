package dev.task.dndquest.validator;

import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.validator.annotation.CharacterClassConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CharacterClassConstraintValidator
         implements ConstraintValidator<CharacterClassConstraint, String> {
    @Autowired
    private PlayCharacterClassService playCharacterClassService;

    @Override
    public void initialize(CharacterClassConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {
        return playCharacterClassService.existsByName(value);
    }
}
