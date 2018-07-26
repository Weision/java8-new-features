package com.wxx.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamClient {
    public static void main(String[] args) {
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
        Stream<Date> newStream3 = Stream.generate(() -> {
            return new Date();
        });
        newStream3.forEach(System.out::println);

        //5 String chars or String tokens
        System.out.println("-------------String chars()-------------");
        IntStream newStream4 = "12345_abcdefg".chars();
        newStream4.forEach(System.out::println);
        // OR
        Stream<String> newStream5 = Stream.of("A$B$C".split("\\$"));
        newStream5.forEach(System.out::println);


        //流的操作
        List<String> strings = Arrays.asList("", "ddd", "eee", "ccc", "", "bbb", "aaa", "ggg", "fff");
        //1 stream() 为集合创建串行流
        Stream<String> stream = strings.stream();
        //2 filter() 根据条件过滤元素
        long count = stream.filter(string -> !string.isEmpty()).count();
        System.out.println("-------------filter()-------------");
        System.out.println("stream中有[" + count + "]个非空数据!");
        //3 forEach() 来迭代流中的每个数据
        System.out.println("-------------forEach()-------------");
        strings.stream().forEach(System.out::println);
        //4 map() 遍历每个元素的value
        System.out.println("-------------map()-------------");
        strings.stream().map(i -> i + "@@!").forEach(System.out::println);
        //5 limit() 获取指定数量的流
        System.out.println("-------------limit()-------------");
        strings.stream().limit(4).forEach(System.out::println);
        //6 sorted 方法用于对流进行排序
        System.out.println("-------------sorted()-------------");
        strings.stream().sorted().forEach(System.out::println);


        //7 parallelStream() 为集合创建并行流
        System.out.println("-------------parallelStream()-------------");
        long count1 = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println("stream中有[" + count1 + "]非空数据!");

        //8 Collectors 实现了很多归约操作,如将流转换成集合和聚合元素,Collectors 可用于返回列表或字符串
        System.out.println("-------------Collectors List-------------");
        System.out.println(strings.stream().filter(a -> !a.isEmpty()).collect(Collectors.toList()).toString());

        System.out.println("-------------Collectors String-------------");
        System.out.println(strings.stream().filter(a -> !a.isEmpty()).collect(Collectors.joining(",")));

        //9 distinct() 去重过滤
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("-------------distinct()-------------");
        numbers.stream().map(n -> n * n).distinct().forEach(System.out::println);

        //10 summaryStatistics() 统计结果收集器
        System.out.println("-------------summaryStatistics()-------------");
        IntSummaryStatistics iss = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + iss.getMax());
        System.out.println("列表中最小的数 : " + iss.getMin());
        System.out.println("所有数之和 : " + iss.getSum());
        System.out.println("平均数 : " + iss.getAverage());

    }

}
