package dev.task.dndquest.validator.annotation;

import dev.task.dndquest.validator.CharacterClassConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharacterClassConstraintValidator.class)
public @interface CharacterClassConstraint {
    String message() default "{wrong.character.class}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
