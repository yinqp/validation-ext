package validate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.time.DateUtils;
import validate.DateTimeString;

import java.text.ParseException;
import java.util.Date;


public class DateTimeStringValidator implements ConstraintValidator<DateTimeString, String> {
    private String pattern;

    @Override
    public void initialize(DateTimeString constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            Date date = DateUtils.parseDate(value,pattern);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}