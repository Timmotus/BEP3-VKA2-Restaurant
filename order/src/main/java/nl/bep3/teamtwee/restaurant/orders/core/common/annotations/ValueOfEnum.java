package nl.bep3.teamtwee.restaurant.orders.core.common.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import nl.bep3.teamtwee.restaurant.orders.core.common.annotations.validator.ValueOfEnumValidator;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "default message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
