package fanxing;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class InterImpl1 implements Inter<String> {
    @Override
    public void show(String s) {
        System.out.println(s);
    }
}
