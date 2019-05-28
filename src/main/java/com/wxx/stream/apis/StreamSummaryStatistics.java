package com.wxx.stream.apis;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class StreamSummaryStatistics {
    public static void main(String[] args) {
        //14 summaryStatistics() 统计结果收集器
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        System.out.println("-------------summaryStatistics()-------------");
        IntSummaryStatistics iss = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + iss.getMax());
        System.out.println("列表中最小的数 : " + iss.getMin());
        System.out.println("所有数之和 : " + iss.getSum());
        System.out.println("平均数 : " + iss.getAverage());

    }
}
