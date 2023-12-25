package dev.task.dndquest.validator.annotation;

import dev.task.dndquest.validator.ItemConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemConstraintValidator.class)
public @interface ItemConstraint {
    String message() default "{wrong.item.name}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
