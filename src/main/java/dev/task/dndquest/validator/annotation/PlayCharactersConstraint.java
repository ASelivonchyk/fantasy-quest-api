package dev.task.dndquest.validator.annotation;

import dev.task.dndquest.validator.PlayCharacterConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PlayCharacterConstraintValidator.class)
public @interface PlayCharactersConstraint {
    String message() default "{wrong.character.value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
