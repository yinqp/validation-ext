package validate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.ObjectUtils;
import validate.LeastOne;
import validate.Same;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 标记哪些类必须一样
 */
public class SameValidator implements ConstraintValidator<Same, Object> {
    private String[] fields;

    @Override
    public void initialize(Same same) {
        String[] value = same.value();
        this.fields =value !=null&&value.length>0?value: same.fields();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //注解加在实体类上面
        if(value==null){
            return true;
        }
        Field[] fieldAttr = getValues(value,fields);
        if(fieldAttr==null||fieldAttr.length<2){
            return true;
        }else if(fieldAttr.length==2){
            try {
                fieldAttr[0].setAccessible(true);
                fieldAttr[1].setAccessible(true);
                return ObjectUtils.nullSafeEquals(fieldAttr[0].get(value),fieldAttr[1].get(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            boolean same = false;
            for (int i = 0; i < fieldAttr.length; i++) {
                for (int j = i+1; j < fieldAttr.length; j++) {
                    Field fi = fieldAttr[i];
                    Field fj = fieldAttr[j];
                    fi.setAccessible(true);
                    fj.setAccessible(true);
                    try {
                        if(!ObjectUtils.nullSafeEquals(fi.get(value), fj.get(value))){
                            same=true;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return !same;
        }
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
