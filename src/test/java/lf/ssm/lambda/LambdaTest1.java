package lf.ssm.lambda;

import lf.ssm.entity.test.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.util.Assert.notNull;

/**
 * @Classname LambdaTest1
 * @Date 2019/7/21 12:45
 * @Created by 刘锋
 */


@Slf4j
public class LambdaTest1 {

    @Test  //lambda遍历数组
    public void lambdaTest(){
        final Integer[] temp = {null};
        List<Integer> list = Arrays.asList(2, 1, 3, 6, 5, 4, 9);
        list.forEach(i->{
            temp[0] =i;
        });

        for (Integer i:temp){
            System.out.println(i);
        }

        String a = "a";
        System.out.println(Optional.ofNullable(a).orElse("c").contains("a"));

    }


    @Test
    public void testlambda2(){
        String str="取两次1:%1$s %1$s,取两次2:%2$s %2$s";
        System.out.println(String.format(str, "111","222"));


    }

    @Test
    public void testOthers(){

        Integer[] ids={1,2,3};
        Integer[] ids2=ids;

        ids[1]=3;
        System.out.println(ids==ids2);
    }

    @Test
    public void testLambdaMap(){
        //将集合中的所有值 +100
        List<Integer> numbers= Arrays.asList(100, 200, 300, 400);
        List<Integer> collect = numbers.stream().map(n -> n = n + 200).collect(Collectors.toList());
        System.out.println(collect);

        //
        List<User> users=new ArrayList<>();
        users.add(new User("刘锋1", 22));
        users.add(new User("刘锋2", 22));
        users.add(new User("刘锋3", 22));

        List<User> collect1 = users.stream().map(u -> u.setAge(u.getAge() + 10)).collect(Collectors.toList());
        System.out.println(collect1);

    }


    @Test
    public void  testSortLambda(){
        String[] names = {"喵","汪","咩"};
        Arrays.sort(names, (String s1, String s2) -> (s1.compareTo(s2)));
        System.out.println(Arrays.asList(names));

        List<User> users=new ArrayList<>();
        users.add(new User("張三", 23));
        users.add(new User("李四", 25));
        users.add(new User("王五", 18));

        List<User> collect = users.stream().sorted((u1, u2) -> u1.getAge() - u2.getAge()).collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testIntlambda(){
       // IntStream
        int[] nums={22,33,66,88};

        //求和
        int sum = IntStream.of(nums).sum();

        //最大值
        int max = IntStream.of(nums).max().getAsInt();

        //最小值
        int min = IntStream.of(nums).min().getAsInt();

        System.out.println(sum);
        System.out.println(max);

    }




    @FunctionalInterface// 标识函数式接口:只有一个抽象方法
    interface FunOne{
       Integer getMax(Integer a,Integer b);
    }

    @FunctionalInterface
    interface FunTwo{
        String getUpcase(String str);

        //1.8 新特性default关键字:用于接口中标识默认的方法
        default String showMsg(String msg){
            return msg;
        }
    }

    interface FunParent extends FunOne,FunTwo{

    }

    @Test
    public void testFunInterface(){
        FunOne f1=  (a,b)-> a>b? a : b;
        FunTwo f2=  (a)-> a.toUpperCase();
    }


    @Test
    public void  testPredicate(){
        //Predicate 断言型接口
        Predicate<Integer> predicte = p-> p>0;

    }

    @Test
    public  void  testAss(){
        User u=new User();
        u=null;
        notNull(null);

    }


    @Test
    public void testOption1(){


        String str = Optional.ofNullable("刘锋").map(s -> s + "刘锋最帅").orElse("刘锋最丑");
        System.out.println(str);

    }



    @Test
    public void testIdeaKey(){

        String str = Optional.ofNullable("刘锋").map(s -> s + "刘锋最帅").orElse("刘锋最丑");

        System.out.println(str);

    }


    private static Integer get(Integer s) {
        System.out.println(s + "：~~我执行了~~");
        return s;
    }
}
