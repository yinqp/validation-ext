package validate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.core.annotation.AliasFor;
import validate.validator.LeastOneValidator;

import java.lang.annotation.*;

/**
 * 同一个类中或同一个类中不同字段或方法上，至少一个值不为空
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LeastOneValidator.class)
@Documented
public @interface LeastOne {
    String message() default "{至少有一个属性不为空}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @AliasFor("value")
    String[] fields() default {};
    @AliasFor("fields")
    String[] value() default {};
}
