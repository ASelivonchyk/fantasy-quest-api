package dev.task.dndquest.validator;

import dev.task.dndquest.service.PlayCharacterClassService;
import dev.task.dndquest.validator.annotation.CharacterClassConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CharacterClassConstraintValidator
         implements ConstraintValidator<CharacterClassConstraint, String> {
    private final PlayCharacterClassService playCharacterClassService;

    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {
        return playCharacterClassService.existsByName(value);
    }
}
