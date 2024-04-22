package validate.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.ObjectUtils;
import validate.LeastOne;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class LeastOneValidator implements ConstraintValidator<LeastOne, Object>{
    private String[] fields;

    @Override
    public void initialize(LeastOne atLeastOneNotEmpty) {
        String[] value = atLeastOneNotEmpty.value();
        this.fields =value !=null&&value.length>0?value: atLeastOneNotEmpty.fields();
    }


    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        //注解加在实体类上面
        if(object==null){
            return true;
        }
        Field[] fieldAttr = getValues(object,fields);
        if(fieldAttr!=null&&fieldAttr.length>0){
            for (int i = 0; i < fieldAttr.length; i++) {
                fieldAttr[i].setAccessible(true);
                try {
                    Object o = fieldAttr[i].get(object);
                    if (!ObjectUtils.isEmpty(o)) {
                        return true;
                    }
                } catch (IllegalAccessException e) {}
            }
        }
        return false;
    }

    private Field[] getValues(Object object, String[] fields) {
        List<Field> attrs = new ArrayList<>();
        Class<?> aClass = object.getClass();
        if(fields!=null&&fields.length!=0){
            for (String f :fields) {
                try {
                    attrs.add(aClass.getDeclaredField(f));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //没有有效的属性(带有注解字段的)
        if(attrs.isEmpty()){
            attrs.addAll(Arrays.asList(aClass.getDeclaredFields()));
        }
        return attrs.toArray(new Field[0]);
    }
}
