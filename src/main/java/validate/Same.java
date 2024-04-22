package validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.core.annotation.AliasFor;
import validate.validator.LeastOneValidator;
import validate.validator.SameValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameValidator.class)
@Documented
public @interface Same {
    String message() default "参数必须一样}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * 需要检查的字段
     * @return
     */
    @AliasFor("value")
    String[] fields() default {};

    @AliasFor("fields")
    String[] value() default {};
}
