package Lambda;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangkai
 * @date 31/1/21
 */
public class StreamTest {

    /*
     * of
     *
     * 可接受一个泛型对象或可编程泛型集合，构造一个Stream对象
     * */
    public static String createStream() {
        return Stream.of("a", "b", "c").toString();
    }

    /*
     * concat
     *
     * 连接两个Stream，不改变其中任意一个Stream，生成新的Stream
     * */
    public static void concatStream() {
        Stream<String> a = Stream.of("a", "b", "c");
        Stream<String> b = Stream.of("d", "e");
        Stream<String> c = Stream.concat(a, b);
        List<String> lst = c.collect(Collectors.toList());
        for (String str : lst) {
            System.out.println(str);
        }
    }

    /*
     * max
     *
     * 一般用于求数字集合中的最大值，或者按实体中数字类型的属性比较，拥有最大值的那个实体。它接收一个Comparator<T>，
     * 上面也举了这个例子了，他是一个函数式接口类型，专门用作定义两个对象之间的比较，例如下面的方法使用了Integer::compareTo这个方法引用
     * */
    public static void max1() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 100);
        Integer maxNum = integerStream.max(Integer::compareTo).get();
        System.out.println(maxNum);
    }

    public static void max2() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 200);
        Integer maxNum = integerStream.max((a, b) -> {
            if (a == b) {
                return 0;
            }
            if (a < b) {
                return -1;
            }
            return 1;
        }).get();
        System.out.println(maxNum);
    }


    /*
     * findFirst: 获取Stream中的第一个元素
     * findAny: 获取Stream中的某个元素，如果是串行情况下，一般都会返回第一个元素，并行情况下就不一定了
     * count: 返回元素个数
     * skip: 跳过前n条数据
     * distinct: 元素去重
     * sorted: 有两个重载，一个无参，一个有个Comparator的参数
     * */

    // 创建数据
    public static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }


    /*
     * filter: 用于条件筛选过滤，筛选出符合条件的数据。举例：性格为0，年龄大于50的记录
     * map: 接受一个Function的函数式接口，通过原始数据元素，映射出新的类型。
     * mapToInt: 将元素转换成int
     * mapToLong
     * mapToDouble
     * flatMap: 将多维结构扁平化，通过flatMap方法，可以将如Stream<String[]>、Stream<Set<String>>、Stream<List<String>>
     *           等结构转化成Stream<String>
     * flatMapToInt
     * flatMapToLong
     * flatMapToDouble
     * collection: 在进行了一些列操作之后，我们的目的是为了将结果编程List/Map等常用的数据结构，collection就是为了实现这个目的
     * toArray: collection是返回列表、Map等，而toArray是返回数组，有两个重载，空参数的返回Object[]，另一个接受一个IntFunction<R>类型参数
     * reduce: 每次计算的时候都用到上一次的计算结果，比如求和操作，前两个数的和加上第三个数的和...一直到最后一个数
     * */


    /*
    * 并行Stream
    * Stream本质上来说是用来所做数据处理的，为了加快处理速度，Stream API提供了并行Stream处理的方式，通过users.parallelStream()
    * 或者users.stream().parallel()的方式创建并行Stream对象，支持的API与普通的Stream几乎一致
    *
    * 并行的Stream默认使用ForkJoinPool线程池，当然也支持自定义，不过一般情况下没有必要。ForkJoin框架的分治策略与并行流处理正好契合。
    *
    * 什么情况下使用并行Stream？
    * 1. 多核计算机
    * 2. 数据量较大的情形
    * 3. CPU密集型计算，IO密集型使用并行反而更慢
    * 4. 合并代价大时不适用
    * 5. limit/findFirst/forEachOrdered等依赖于元素顺序的操作不适用
    *
    * */

    public static void main(String[] args) {
        //max1();
        //max2();

        //Stream<String> stream = Stream.of("a","b","c");
        //System.out.println(stream.findAny().get());

        //concatStream();

        //List<Integer> lst= new ArrayList<>();
        //lst.add(1);
        //lst.add(2);
        //lst.add(3);
        //Stream.of(lst).forEach(System.out::print);

        //Stream<Integer> streamInteger = Stream.of(1, 1, 2, 3, 3, 5, 6, 5);
        //streamInteger.distinct().forEach(e-> System.out.println(e));

        //Stream<String> streamStr = Stream.of("a1","b3","c6");
        //streamStr.sorted((x,y)->{
        //    if (x.charAt(1) > y.charAt(1)) {
        //        return 1;
        //    } else {
        //        return -1;
        //    }
        //}).forEach(System.out::println);


        List<User> users = getUserData();
        Stream<User> stream = users.stream();
        //stream.filter(user->(user.getGender()==0 && user.getAge()>50)).forEach(System.out::println);

        //List<String> nameLst = stream.map(user -> user.getUserName()).collect(Collectors.toList());
        //nameLst.stream().forEach(System.out::println);

        //List<String> nameList = users.stream().map(user -> user.getPhone()).collect(Collectors.toList());
        //nameList.stream().mapToInt(a -> Integer.parseInt(a)).forEach(System.out::println);

        // List<UserDto> userDtos = userList.stream().flatMap(subUserList -> subUserList.stream()).collect(Collectors.toList());

        //Map<Integer, List<User>> map1 = users.stream().collect(Collectors.groupingBy(User::getUserId));
        //Map<Integer, Long> map2 = users.stream().collect(Collectors.groupingBy(User::getUserId, Collectors.counting()));
        //User[] userArray = users.stream().filter(user -> user.getGender() == 0).toArray(User[]::new);

        //Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        //int sum = integerStream.reduce(10,(x,y)->x+y);
        //System.out.println(sum); // 20
    }
}