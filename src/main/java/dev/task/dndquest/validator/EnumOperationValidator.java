package dev.task.dndquest.validator;

import dev.task.dndquest.validator.annotation.EnumOperation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class EnumOperationValidator implements ConstraintValidator<EnumOperation, String> {
    private List<String> enumValues;

    @Override
    public void initialize(EnumOperation constraintAnnotation) {
        enumValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(e -> e.name().toLowerCase()).toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cntext) {
        return enumValues.contains(value);
    }
}
