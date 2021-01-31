package Lambda;

/**
 * @author zhangkai
 * @date 30/1/21
 */
public class TestLambda2 {
    public static void main(String[] args) {
        ILove love = new Love();
        love.love(2);

        love = (int a)->{
            System.out.println("I Love You->"+a);
        };
        love.love(3);



        love = (a)->{
            System.out.println("I Love You->"+a);
        };
        love.love(4);

        love = a->{
            System.out.println("I Love You->"+a);
        };
        love.love(5);

        love = a-> System.out.println("I Love You->"+a);
        love.love(6);


        /*
        * 总结：
        *   1. lambda表达式只能有一行代码的情况下才能简化成一行，否则使用代码块
        *   2. 前提是接口为函数式接口
        *   3. 多个参数也可以去掉参数类型，要去掉就都去掉
        * */
    }
}

interface ILove {
    void love(int a);
}

class Love implements ILove {
    @Override
    public void love(int a) {
        System.out.println("I Love You->"+a);
    }
}