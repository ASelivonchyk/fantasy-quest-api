package dev.task.dndquest.validator.annotation;

import dev.task.dndquest.validator.RaceConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RaceConstraintValidator.class)
public @interface RaceConstraint {
    String message() default "{wrong.race}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
