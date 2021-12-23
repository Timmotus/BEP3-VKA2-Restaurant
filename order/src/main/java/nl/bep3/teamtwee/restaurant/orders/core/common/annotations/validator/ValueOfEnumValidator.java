package nl.bep3.teamtwee.restaurant.orders.core.common.annotations.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nl.bep3.teamtwee.restaurant.orders.core.common.annotations.ValueOfEnum;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> values;

    @Override
    public void initialize(ValueOfEnum annotation) {
        values = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("must be any of " + values.toString()).addConstraintViolation();

        return values.contains(value.toString());
    }
}
