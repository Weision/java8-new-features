package com.weison.functionalInterface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Client {
    public static void main(String[] args) {

        //静态方法直接调用
        CommonInterface.staticMethod();
        CommonInterface.anotherStaticMethod();
        CommonInterface1.staticMethod();
        CommonInterface1.anotherStaticMethod();

        //默认方法　和　抽象方法需实现类实例化后调用
        CommonInterface commonInterface = new CommonInterfaceImpl();
        //抽象方法重写后调用
        commonInterface.doSomthing();
        //默认方法重写后调用
        commonInterface.defaultMehtod();
        commonInterface.anotherDefaultMehtod();


        //消费型：第二个参数为实现了Consumer<T>接口accept(T t)方法的lambda表达式
        buy(1000, money -> System.out.println("消费型-->我买面包花了:" + money + "元"));
        //供给型：参数为实现了Supplier<Integer>接口get()方法的lambda表达式
        String phone = factory(() -> {
            return "Apple8";
        });
        System.out.println("供给型-->富士康生产的有名的手机是:" + phone);
        //函数型：参数为实现了Function<String, Integer>接口apply(T t)方法的lambda表达式
        Integer num = convert("666", x -> Integer.parseInt(x));
        System.out.println("函数型-->字符型666被转换成了数字: " + num);
        //断言型：参数为实现了Predicate<String>接口test(T t)方法的lambda表达式
        String na = "尼古拉斯赵四";
        String noble = isNoble(na, (name) -> !"".equals(name) && name.contains("尼古拉斯"));
        System.out.println("断言型-->从你的名字就可以看出你是个：" + noble);

    }

    /**
     * buy方法有个Consumer<Integer>的入参
     *
     * @param money
     * @param consumer
     */
    public static void buy(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    /**
     * factory方法有个Supplier<Integer>的入参
     *
     * @param supplier
     * @return
     */
    public static String factory(Supplier<String> supplier) {
        return supplier.get();
    }


    /**
     * convert方法有个Function<String, Integer>的入参
     *
     * @param str
     * @param function
     * @return
     */
    public static Integer convert(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    //断言型 Predicate<String>

    /**
     * isNoble方法有个Predicate<String>的入参
     *
     * @param name
     * @param predicate
     * @return
     */
    public static String isNoble(String name, Predicate<String> predicate) {
        if (predicate.test(name))
            return "贵族";
        return "平民";
    }
}
