package com.weison.stream.apis;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamParalleStream {
    public static void main(String[] args) {
        //15 parallelStream() 为集合创建并行流
        System.out.println("-------------parallelStream()-------------");
        Stream<String> parallelStream = Arrays.asList("555", "333", "444", "111", "222", "666").parallelStream();
        long count2 = parallelStream.filter(s -> !s.isEmpty()).count();
        System.out.println("stream中有[" + count2 + "]非空数据!");
    }
}
