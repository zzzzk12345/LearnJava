package annotation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class PersonDAO {
    private Person person;

    public Person getPerson() {
        return person;
    }

    // 把对象注入到方法上（基本信息的升级版）
    // 使用注解将Person对象注入到setPerson()方法中，从而设置了PersonDAO类的person属性
    @InjectPerson(age = 25)
    public void setPerson(Person person) {
        this.person = person;
    }

    public static void main(String[] args) throws IntrospectionException, IllegalAccessException, InstantiationException {

        /*注解的作用：
        * 1. 让编译器检查代码
        * 2. 将数据注入到方法、成员变量、类上
        * */


        /*
        * 得到想要类中注入的属性
        * 得到该属性的对象
        * 得到属性对应的写方法
        * 通过写方法得到注解
        * 获取注解详细的信息
        * 将注解的信息注入到对象
        * 调用属性写方法，将已填充数据的对象注入到方法中
        *
        * */


        // 1. 使用内省(后面需要得到属性的写方法)，得到想要注入的属性
        PropertyDescriptor descriptor = new PropertyDescriptor("person", PersonDAO.class);
        // 2. 得到想要注入属性的具体方法
        Person person = (Person) descriptor.getPropertyType().newInstance();
        // 3. 得到该属性的写方法setPerson()
        Method method = descriptor.getWriteMethod();
        // 4. 得到写方法的注解
        InjectPerson injectPerson = method.getAnnotation(InjectPerson.class);
        // 5. 得到注解上的信息(注解的成员变量就是用Method定义的)
        Method[] methods = injectPerson.getClass().getMethods();
        // 6. 将注解上的信息填充到person对象上
        for (Method m : methods) {
            // 得到注解上属性的名字 userName或者age
            String name = m.getName();
            // 查看person对象有没有与之对应的方法 (setName(),setAge())
            try {
            // 6.1 假设有，得到对应方法
            PropertyDescriptor descriptor1 = new PropertyDescriptor(name, Person.class);
            Method method1 = descriptor1.getWriteMethod();

            // 得到注解中的值
            Object o = m.invoke(injectPerson, null);

            // 调用Person对象的setter方法，将注解上的值设进去
            method1.invoke(person, o);
            } catch (Exception ignored) {
                // 6.2 Person对象没有与之对应的方法，继续
            }
        }
        // 7. 将person对象赋给PersonDAO
        PersonDAO personDAO = new PersonDAO();
        try {
            method.invoke(personDAO, person);
            System.out.println(personDAO.getPerson().getUserName());
            System.out.println(personDAO.getPerson().getAge());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
