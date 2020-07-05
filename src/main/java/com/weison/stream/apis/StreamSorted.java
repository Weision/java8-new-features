package com.weison.stream.apis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamSorted {
    public static void main(String[] args) {
//获取数组流
        List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();

        //11 sorted 对流元素进行排序
        System.out.println("-------------sorted 对流元素进行排序-------------");
        strings.stream().sorted().forEach(System.out::println);
    }
}
