package Lambda;

/**
 * @author zhangkai
 * @date 30/1/21
 */

/*
* 推导lambda表达式
* */
public class TestLambda1 {

    // 3. 静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I Like Lambda2");
        }
    }


    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4. 局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        // 5. 匿名内部类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda4");
            }
        };
        like.lambda();

        // 6. 用lambda简化
        like = ()->{
            System.out.println("I Like Lambda5");
        };
        like.lambda();


    }
}

/*
* 理解函数式接口是学习Java8 Lambda表达式的关键
*
* 函数式接口的定义：
*  - 任何接口，如果只包含唯一一个抽象方法，那么他就是一个函数式接口
*  - 对于函数式接口，我们可以通过lambda表达式来创建该接口的对象
*
* */

// 1. 定义一个函数式接口
interface ILike {
    void lambda();
}

// 2. 实现类
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}