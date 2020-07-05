package com.weison.stream.apis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPeek {
    public static void main(String[] args) {
//获取数组流
        List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();

        //9 peek 生成一个带有作用于每个元素的消费型lambda表达式的Stream，在Stream元素被消费时，该lambda表达式会被执行
        System.out.println("-------------peek 元素消费时执行传入的lambda表达式-------------");
        strings.stream().peek(element -> System.out.println("元素被消费时执行-->" + element)).forEach(System.out::println);
    }
}
