package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhangkai
 * @date 1/2/21
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String userName();

    int age();
}
