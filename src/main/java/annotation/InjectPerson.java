package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhangkai
 * @date 1/2/21
 */


@Retention(RetentionPolicy.RUNTIME)
// 使用注解将Person对象注入到setPerson()方法中，从而设置了PersonDAO类的person属性
public @interface InjectPerson {
    String userName() default "Melo";

    int age();
}
