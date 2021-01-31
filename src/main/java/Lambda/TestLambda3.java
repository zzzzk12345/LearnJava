package Lambda;

/**
 * @author zhangkai
 * @date 30/1/21
 */
public class TestLambda3 {
    // 测试线程
    public static void main(String[] args) {
        new Thread(()-> System.out.println("快速新建并启动一个线程")).run();
    }
}
