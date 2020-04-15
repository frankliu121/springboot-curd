package lf.ssm;

import lf.ssm.enums.ResultEnum;
import lf.ssm.job.TestJob;
import lf.ssm.util.AgeUtil;
import lf.ssm.util.JsonUtil;
import lf.ssm.util.MD5;
import lf.ssm.entity.sys.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * @Classname UtilsTest
 * @Date 2019/4/19 17:15
 * @Created by 刘锋
 */

@Slf4j
public class UtilsTest {

    @Test
    public void testEnum() {
        int a = ResultEnum.ERR_PARAM.code;
        System.out.println();
        System.out.println(ResultEnum.ERR_PARAM.value);
    }

    @Test
    public void testAgeUtil() {
        int i = AgeUtil.IdNOToAge("431103199901210037");

        System.out.println(i);
    }

    @Test
    public void testJsonUtil() {
        User user = new User();
//        user.setIp("sdf");
//        user.setUsername("测试");

        String s = JsonUtil.toJson(user);
        User user1 = JsonUtil.toBean(s, User.class);
        System.out.println(user1);
        System.out.println(s);
    }

    @Test
    public void testMD5() {
        String s = MD5.MD5Encode("123456");
        System.out.println(s);
    }

    @Test
    public void log() {
        System.out.println("测试啊啊啊");
    }


    //语法格式1 无参数,无返回值
    @Test
    public void testLamda() {
//        Runnable thread1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("线程启动了");
//            }
//        };
//
//        Thread thread = new Thread(thread1);
//        thread.run();

        //无参数,无返回值  (个人总结,相当于将new Runable省略掉了,直接写方法体)
        Runnable thread1 = () -> System.out.println("线程启动了");
        thread1.run();
    }


    //语法2 有参数无返回值
    @Test
    public void lambdaTest2() {
        //语法3 若只有一个参数,则小括号可以不写
        Consumer<String> consumer = e -> System.out.println("ghijhkhi" + e);
        consumer.accept("woojopj");
    }


    //语法4
    //若有多个参数,有返回值,且有多条语句记得要用要用{}括起来
    @Test
    public void lambdaTest4() {
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };

        int compare = comparator.compare(2, 3);
        System.out.println(compare);
        //语法格式5 如果只有一行代码可以不用写{}和return

        Comparator<Integer> c1 = (x, y) -> -1; //comparator.compare(x, y)
        int compare1 = c1.compare(3, 4);
        System.out.println(compare1);
    }

    //测试 :: 语法
    @Test
    public void lambdaTest6() {
        Comparator<Integer> c1 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> c2 = Integer::compare;

        c1.compare(1, 2);
        c2.compare(2, 3);
    }

    //实际引用 ,遍历集合

    //Consumer接口实例  特点: 引用的函数无返回值,且只有一个参数
    @Test
    public void lambdaConsumerTest() {
        //遍历打印元素 方法1
        List<String> str = Arrays.asList("1", "2", "3");
        str.forEach(System.out::println);
        str.stream().forEach((x)->{
            System.out.printf("%s哈哈哈这种的字符",x);
        });

    }

    //lambda结合stream的使用对数据进行过滤
    @Test
    public void testStream(){
        //过滤出大于二的数据 且不等于5的数据 并打印
        Predicate<Integer> predicate= x->x!=5;
        predicate.and(x->x>2);
        //todo 怎么是true呢
        boolean test = predicate.test(1);
        Arrays.asList(1,2,3,4,5,6,7).stream().filter(x->x!=5&&x>2).forEach(System.out::println);
        System.out.println(test);
    }


    @Test
    public void testStream2(){
        List<Integer> list = Arrays.asList(2, 1, 3, 6, 5, 4, 9);
        //过滤
        list.stream().filter(x->x!=5&&x>2).forEach(System.out::print);
        System.out.println();
        //排序  (如果sorted方法不指定比较器Comparator则默认使用自然排序)
        list.stream().sorted(Integer::compareTo).forEach(System.out::print);
        List<Integer> collect = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("再装换成list:"+collect);
        System.out.println();
        //map映射  将元素全部装换成大写并输出
        List<String> list2 = Arrays.asList("aaa", "bb", "c");
        list2.stream().map(x-> x.toUpperCase()).forEach(System.out::print);
    }


    //Comparator  比较接口
    @Test
    public void objectMethod(){
        Comparator<Integer> c1=(x,y)->Integer.compare(x,y);
        Comparator<String> c2=(u1,u2)->{
            System.out.println("就是对不上");
            return -1;
        };

    }


    //Optional 接口
    public void testOption(){
        Optional<String> bam = Optional.of("bam");
        bam.isPresent();  //判断不为null  true
        bam.ifPresent(System.out::println);  //bam
        bam.orElse("123");  //如果bam为null则返回123
        String s = bam.get();  //bam


    }





    //Supplier 消费型接口 无参 有返回值
    @Test
    public void testSupplier(){
        User user=new User();
//        user.setUsername("测试Supplier接口 消费型");
//        Supplier<String> supplier=user::getUsername;
//        String s = supplier.get();
//        System.out.println(s);
    }


    //Function 型接口  特点 有参数,有返回值

    @Test
    public void  testFunction(){
        Function<String,Integer> function=Integer::valueOf;
        Integer i = function.apply("123");
        System.out.println(i);
    }
    //Predicte  判定对象是否满足某一约束  包含方法 and or ...
    @Test
    public void  testpredicte(){
        User user = new User().setAge(12);
        Predicate<Integer> u= (x)-> x.equals(1);
        u.and((x)->{
            System.out.println("and条件");
            return x!=3;
        });
        boolean test = u.test(1);
        System.out.println(test);

    }





    @Test
    public void LambdaTest4() {
        //
        Consumer<String> c=(x)-> System.out.println(x);  // 供给型 对对象进行操作包含方法 void accept(T t)

        Supplier<Integer> supplier=()-> 1;   //消费型接口 包含方法 get()  获取值
        Integer o = supplier.get();
        Function<String, String> function = (x)->"123456".substring(0, 2);


        c.accept("供给性接口,操作思想:将函数供给,需要用的时候调用");
        System.out.println("消费性接口:当需要消费时获取"+o);

        Consumer<Integer> sout = System.out::println;  //:: 标识引用这个函数体
        sout.accept(1);

    }

    // 测试 :: 获取
    @Test
    public void testLambda9(){

        User user = new User();
    }


    @Test
    public void LambdaTest8() {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        // 使用 lambda 表达式以及函数操作(functional operation)
        //players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

    }

    @Test
    public void testBeanUtil() throws InvocationTargetException, IllegalAccessException {
//        User user = new User();
//        user.setId(1249180919802159105L);
//        user.setUsername("刘锋");
//        user.setCreateTime(new Date());
//
//        //bean 转换成 Map
//        Map<String,Object> objectMap = BeanUtil.beanToMap(user);
//        System.out.println(objectMap);
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        map.put("username","liufeng");
        map.put("age",20);

        Set<String> keys = map.keySet();


        BeanUtils.populate(user,map);
        System.out.println(user);
    }



    /**
     * 获取属性名数组
     * */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }


    /* 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    @Test
    public void testClas() throws IllegalAccessException, InstantiationException {
        Class<User> claz = User.class;
        User user = claz.newInstance();
        System.out.println(user);
        Class<TestJob> job = TestJob.class;
        TestJob testJob = new TestJob();
        testJob.printLoveYou();
    }



}

