package com.wxx.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamClient {
    public static void main(String[] args) {
        //creadeStream();
        operateStream();

    }

    public static void creadeStream() {
        //创建流的方式
        //１ Stream.of(val1, val2, val3….)
        System.out.println("-------------Stream.of(val1)-------------");
        Stream<Integer> newStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        newStream.forEach(System.out::println);
        //2 Stream.of(arrayOfElements)
        System.out.println("-------------Stream.of(arrayOfElements)-------------");
        Stream<Integer> newStream1 = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        newStream1.forEach(System.out::println);
        //3 someList.stream()
        System.out.println("-------------someList.stream()-------------");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Stream<Integer> newStream2 = list.stream();
        newStream2.forEach(System.out::println);
        //4 Stream.generate() or Stream.iterate()
        System.out.println("-------------Stream.generate()-------------");
        /*Stream<Date> newStream3 = Stream.generate(() -> {
            return new Date();
        });
        newStream3.forEach(System.out::println);*/

        //5 String chars or String tokens
        System.out.println("-------------String chars()-------------");
        IntStream newStream4 = "12345_abcdefg".chars();
        newStream4.forEach(System.out::println);
        // OR
        Stream<String> newStream5 = Stream.of("A$B$C".split("\\$"));
        newStream5.forEach(System.out::println);
    }

    public static void operateStream() {
        //获取数组流
        List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();

        //1 forEach 迭代流中的元素
        System.out.println("-------------forEach 遍历stream中的元素-------------");
        strings.stream().forEach(System.out::println);

        //2 filter 根据条件过滤流中元素
        //2.1和2.2需分开执行，否则会报错：IllegalStateException: stream has already been operated upon or closed
        System.out.println("-------------filter 过滤stream中符合条件的元素并返回Stream-------------");
        //2.1 过滤获取等于aaa的元素，并打印集合
        //stream.filter(element -> "666".equals(element)).forEach(System.out::println);
        //2.2 过滤非空元素，并获取数组长度
        long count = stream.filter(element -> !element.isEmpty()).count();
        System.out.println("stream中有[" + count + "]个非空数据!");

        //3 map 遍历每个元素的value,对其执行传入的lambda表达式后返回Stream
        System.out.println("-------------map 遍历元素value并执行传入的lambda表达式-------------");
        //完整的使用方法：strings.stream().map(i -> i + "@@!").forEach(System.out::println);
        //拆分成两步：１ 遍历元素value并执行传入的lambda表达式
        Stream<String> stringStream = strings.stream().map(i -> i + "@@!");
        //拆分成两步：2 遍历stream并打印元素
        stringStream.forEach(System.out::println);


        //4 mapToInt(mapToInt/mapToLong/mapToDouble同) 遍历每个元素的value,对其元素类型进行转换
        System.out.println("-------------mapToInt 遍历元素value并执行int转换lambda表达式-------------");
        //完整的使用方法：strings.stream().mapToInt(element -> Integer.valueOf(element)).forEach(System.out::println);
        //拆分成两步：１ 遍历元素value并执行传入的类型转换lambda表达式
        IntStream intStream = strings.stream().mapToInt(element -> Integer.valueOf(element));
        //拆分成两步：2 遍历intStream并打印元素
        intStream.forEach(System.out::println);

        //5 flatMap 把Stream中的层级结构扁平化并返回Stream
        //如List中存在List：
        //在获取第一层stream后再将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字
        //Stream可以容纳不同的数据类型,Stream操作（filter，sum，distinct ...）和collectors不支持它，所以我们需要使用flatMap（）进行以下转换
        System.out.println("-------------flatMap 转换集合内集合(其他对象)-------------");
        String[][] arr = new String[][]{{"333", "444"}, {"111", "555"}, {"666", "222"}};
        //打印
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("-------------flatMap 遍历元素value并执行传入的lambda表达式-------------");
        Arrays.stream(arr).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        //聚合操作　filter
        System.out.println("-------------二维数组未使用flatMap过滤等于666的元素-------------");
        Arrays.stream(arr).filter(element -> "666".equals(element)).forEach(System.out::println);
        System.out.println("-------------二维数组使用flatMap过滤等于666的元素-------------");
        Arrays.stream(arr).flatMap(x -> Arrays.stream(x)).filter(element -> "666".equals(element)).forEach(System.out::println);


        //6 flatMapToInt(flatMapToInt/flatMapToLong/flatMapToDouble) 把Stream中的层级结构扁平化并返回Stream
        System.out.println("-------------flatMapToInt 遍历元素value,执行传入的lambda表达式-------------");
        //6.1 将数组元素转换成int型
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5");
        stringList.stream().flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).forEach(System.out::println);
        //6.2 获取元素长度
        System.out.println("-------------flatMapToInt 遍历元素value,执行传入的lambda表达式-------------");
        List<String> stringList1 = Arrays.asList("Geeks", "GFG", "GeeksforGeeks", "gfg");
        stringList1.stream().flatMapToInt(str -> IntStream.of(str.length())).forEach(System.out::println);

        //7 distinct 遍历每个元素的value,对其进行去重后返回Stream
        System.out.println("-------------distinct 遍历元素value并去重-------------");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().forEach(System.out::println);

        //8 skip 跳过n个元素后返回Stream
        System.out.println("-------------skip 跳过3个元素-------------");
        strings.stream().skip(3).forEach(System.out::println);

        //9 peek 生成一个带有作用于每个元素的消费型lambda表达式的Stream，在Stream元素被消费时，该lambda表达式会被执行
        System.out.println("-------------peek 元素消费时执行传入的lambda表达式-------------");
        strings.stream().peek(element -> System.out.println("元素被消费时执行-->" + element)).forEach(System.out::println);

        //10 limit 获取指定数量的流
        System.out.println("-------------limit 获取4个元素的流-------------");
        strings.stream().limit(4).forEach(System.out::println);

        //11 sorted 对流元素进行排序
        System.out.println("-------------sorted 对流元素进行排序-------------");
        strings.stream().sorted().forEach(System.out::println);

        //12 reduce 把Stream元素组合起来,组合规则为传入的lambda表达式
        //它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合
        //从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
        System.out.println("-------------reduce 把Stream元素组合起来-------------");
        // 字符串连接，concat = "ABCD"
        String str = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("拼接字符串：" + str);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("最小值：" + minValue);
        // 求和，sumValue = 20, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);
        System.out.println("求和有起始值：" + sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("求和无起始值：" + sumValue);

        //13 collect 归约操作,流中的元素累积成一个汇总结果
        //具体的做法是通过定义新的Collector接口来定义的
        System.out.println("-------------collect 汇总结果-------------");
        //13.1 toList toSet toMap
        Stream<String> stream1 = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        List<String> list = stream1.filter(element -> !"666".equals(element)).collect(Collectors.toList());
        System.out.println("汇总结果list-->" + list);
        Stream<String> stream2 = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        Set<String> set = stream2.filter(element -> !"666".equals(element)).collect(Collectors.toSet());
        System.out.println("汇总结果set-->" + set);
        Stream<String> stream3 = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        Map<String, String> map = stream3.filter(element -> !"666".equals(element)).collect(Collectors.toMap(x -> x, x -> x + "1"));
        System.out.println("汇总结果map-->" + map);
        //13.2 joining counting
        Stream<String> stream4 = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        String joining = stream4.map(p -> p).collect(Collectors.joining(","));
        System.out.println("collect joining-->" + joining);
        Stream<String> stream5 = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        Long counting = stream5.map(p -> p).collect(Collectors.counting());
        System.out.println("collect counting-->" + counting);
        //13.3 maxBy groupingBy
        List<Integer> integers = Arrays.asList(555, 333, 444, 111, 222, 666);
        Stream<Integer> stream6 = integers.stream();
        Integer integer = stream6.map(p -> p).collect(Collectors.maxBy(Comparator.comparingInt(o -> o))).get();
        System.out.println("collect integer-->" + integer);

        //13.3 maxBy counting groupingBy


        //14 summaryStatistics() 统计结果收集器
        System.out.println("-------------summaryStatistics()-------------");
        IntSummaryStatistics iss = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + iss.getMax());
        System.out.println("列表中最小的数 : " + iss.getMin());
        System.out.println("所有数之和 : " + iss.getSum());
        System.out.println("平均数 : " + iss.getAverage());

        //15 parallelStream() 为集合创建并行流
        System.out.println("-------------parallelStream()-------------");
        Stream<String> parallelStream = Arrays.asList("555", "333", "444", "111", "222", "666").parallelStream();
        long count2 = parallelStream.filter(s -> !s.isEmpty()).count();
        System.out.println("stream中有[" + count2 + "]非空数据!");
    }
}
