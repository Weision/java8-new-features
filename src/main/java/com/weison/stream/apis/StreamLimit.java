package com.weison.stream.apis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamLimit {
    public static void main(String[] args) {
//获取数组流
        List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();
        //10 limit 获取指定数量的流
        System.out.println("-------------limit 获取4个元素的流-------------");
        strings.stream().limit(4).forEach(System.out::println);
    }
}
