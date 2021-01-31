package Lambda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * @author zhangkai
 * @date 30/1/21
 */
public class APITest {
    public static void main(String[] args) {

        // 作为函数方法的引用
        Function<String, Integer> s = Integer::parseInt;
        System.out.println(s.apply("10"));
        /*
        * 1. 什么样的方法可以被引用？
        * - 全部可以访问到的方法
        * 2. 返回值为什么类型？
        * - 被@FunctionalInterface注解的类型
        * 3. 最关键的是引用方法的参数个数、类型、返回值类型要和函数式接口中的方法声明一一对应。
        * */


        // 自定义函数式接口调用方法
        ZKFunction<LocalDateTime, String, String> zkFunction = FunctionTest::DateFormat;
        System.out.println(zkFunction.run(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss"));

        // 匿名内部类
        String dateStr = new ZKFunction<LocalDateTime, String, String>() {
            @Override
            public String run(LocalDateTime dateTime, String pattern) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
                return dateTime.format(dateTimeFormatter);
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateStr);

        // lambda简化匿名内部类
        ZKFunction<LocalDateTime, String, String> zkFunction1 = (dateTime, pattern) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            return dateTime.format(dateTimeFormatter);
        };
        System.out.println(zkFunction1.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));


    }
}

class FunctionTest {
    public static String DateFormat(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(dateTimeFormatter);
    }
}
