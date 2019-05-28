package com.wxx.stream.apis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamSkip {
    public static void main(String[] args) {
        //获取数组流
        List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();

        //8 skip 跳过n个元素后返回Stream
        System.out.println("-------------skip 跳过3个元素-------------");
        strings.stream().skip(3).forEach(System.out::println);
    }
}
