package dev.task.dndquest.validator.annotation;

import dev.task.dndquest.validator.EnumOperationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumOperationValidator.class)
public @interface EnumOperation {
    Class<? extends Enum<?>> enumClass();
    String message() default "{wrong.item.operation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
