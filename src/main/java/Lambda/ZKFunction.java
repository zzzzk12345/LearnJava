package Lambda;

/**
 * @author zhangkai
 * @date 31/1/21
 */
@FunctionalInterface
public interface ZKFunction<T, S, R> {
    R run(T t, S s);
}
