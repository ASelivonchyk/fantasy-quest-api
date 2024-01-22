package dev.task.dndquest.validator;

import dev.task.dndquest.service.ItemService;
import dev.task.dndquest.validator.annotation.ItemConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemConstraintValidator implements ConstraintValidator<ItemConstraint, String> {
    private final ItemService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return service.existsByName(value);
    }
}
