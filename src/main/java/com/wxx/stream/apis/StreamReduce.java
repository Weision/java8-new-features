package com.wxx.stream.apis;

import java.util.stream.Stream;

public class StreamReduce {
    public static void main(String[] args) {
        //12 reduce 把Stream元素组合起来,组合规则为传入的lambda表达式
        //它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合
        //从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
        System.out.println("-------------reduce 把Stream元素组合起来-------------");
        // 字符串连接，concat = "ABCD"
        String str = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("拼接字符串：" + str);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("最小值：" + minValue);
        // 求和，sumValue = 20, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);
        System.out.println("求和有起始值：" + sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("求和无起始值：" + sumValue);
    }
}
