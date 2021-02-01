package fanxing;

/**
 * @author zhangkai
 * @date 1/2/21
 */
public class ObjectTool<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        ObjectTool<String> tool = new ObjectTool<>();

        tool.setObj("zk");
        String s = tool.getObj();
        System.out.println(s);

        ObjectTool<Integer> objectTool = new ObjectTool<>();
        objectTool.setObj(1);
        System.out.println(objectTool.getObj());
    }
}
