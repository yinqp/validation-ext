package validate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validate.validator.DateTimeStringValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DateTimeStringValidator.class})
public @interface DateTimeString {
    String message() default "{validate.DateTimeString}";
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}