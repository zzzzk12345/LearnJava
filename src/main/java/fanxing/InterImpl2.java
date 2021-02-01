package fanxing;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class InterImpl2<T> implements Inter<T> {
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
