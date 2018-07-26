package com.wxx.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamClient {
    public static void main(String[] args) {


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
        IntSummaryStatistics iss = numbers.stream().mapToInt((x) ->x).summaryStatistics();
        System.out.println("列表中最大的数 : " + iss.getMax());
        System.out.println("列表中最小的数 : " + iss.getMin());
        System.out.println("所有数之和 : " + iss.getSum());
        System.out.println("平均数 : " + iss.getAverage());

    }

}
