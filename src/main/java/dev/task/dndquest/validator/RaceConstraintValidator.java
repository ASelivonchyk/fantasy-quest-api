package dev.task.dndquest.validator;

import dev.task.dndquest.service.RaceService;
import dev.task.dndquest.validator.annotation.RaceConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RaceConstraintValidator implements ConstraintValidator<RaceConstraint, String> {
    @Autowired
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
