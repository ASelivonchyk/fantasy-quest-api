package dev.task.dndquest.validator;

import dev.task.dndquest.service.RaceService;
import dev.task.dndquest.validator.annotation.RaceConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RaceConstraintValidator implements ConstraintValidator<RaceConstraint, String> {
    private RaceService raceService;

    @Override
    public void initialize(RaceConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return raceService.existsByName(value);
    }
}
