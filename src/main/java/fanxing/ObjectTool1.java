package fanxing;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class ObjectTool1 {
    public <T> T show(T t) {
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        ObjectTool1 objectTool1 = new ObjectTool1();
        objectTool1.show(1);
        objectTool1.show("123");
    }
}
