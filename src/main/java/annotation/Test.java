package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class Test {
    // 自定义的注解的基本信息注入到方法上
    @MyAnnotation(userName = "zk", age = 24)
    public void add(String userName, int age) {
        System.out.println(userName+" "+age);
    }

    public static void main(String[] args) {
        try {
            // 1. 反射得到该类的方法
            Class test = Test.class;
            Method method = test.getMethod("add", String.class, int.class);
            // 2. 通过方法得到注解上具体的内容
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            String userName = myAnnotation.userName();
            int age = myAnnotation.age();
            // 3. 将注解上的信息注入到方法
            Test instance = (Test) test.getConstructor().newInstance();
            method.invoke(instance, userName, age);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
