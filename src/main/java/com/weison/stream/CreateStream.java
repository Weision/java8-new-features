package com.weison.stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * CreateStream contains demos how to create a stream
 */
public class CreateStream {
    public static void main(String[] args) {
        creadeStream();

    }

    /**
     * different ways to create stream
     */
    public static void creadeStream() {
        //创建流的方式
        //１ Stream.of(val1, val2, val3….)
        System.out.println("-------------Stream.of(val1)-------------");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.forEach(System.out::println);
        //2 Stream.of(arrayOfElements)
        System.out.println("-------------Stream.of(arrayOfElements)-------------");
        Stream<Integer> stream1 = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream1.forEach(System.out::println);
        //3 someList.stream()
        System.out.println("-------------someList.stream()-------------");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Stream<Integer> stream2 = list.stream();
        stream2.forEach(System.out::println);
        //4 Stream.generate() or Stream.iterate()
        System.out.println("-------------Stream.generate()-------------");
        Stream<Date> stream3 = Stream.generate(() -> new Date());
        stream3.forEach(System.out::println);
        //5 String chars or String tokens
        System.out.println("-------------String chars()-------------");
        IntStream stream4 = "12345_abcdefg".chars();
        stream4.forEach(System.out::println);
        // OR
        Stream<String> stream5 = Stream.of("A$B$C".split("\\$"));
        stream5.forEach(System.out::println);
    }
}
