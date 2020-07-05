package com.weison.stream.apis;

import java.util.Arrays;
import java.util.List;

public class StreamDistinct {
    public static void main(String[] args) {
        //7 distinct 遍历每个元素的value,对其进行去重后返回Stream
        System.out.println("-------------distinct 遍历元素value并去重-------------");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().forEach(System.out::println);
    }
}
