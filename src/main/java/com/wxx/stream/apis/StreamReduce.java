package com.wxx.stream.apis;

import java.util.stream.Stream;

public class StreamReduce {
    public static void main(String[] args) {
        //12 reduce 把Stream元素组合起来,组合规则为传入的lambda表达式
        //它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合
        //从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
        System.out.println("-------------reduce 把Stream元素组合起来-------------");
        // 结果:拼接字符串str：开始：ABCD
        String str = Stream.of("A", "B", "C", "D")
                .reduce("开始：", String::concat);
        // 结果:拼接字符串str1：开始：ABCD
        String str1 = Stream.of("A", "B", "C", "D")
                .reduce("开始：", (s1, s2) -> s1 + s2);
        // 结果:拼接字符串str2：ABCD
        String str2 = Stream.of("A", "B", "C", "D")
                .reduce((s1, s2) -> s1 + s2).orElseGet(String::new);
        // 拼接字符串str3：1A21B21C21D2
        String str3 = Stream.of("A", "B", "C", "D")
                .reduce("", (s1, s2) -> s1 + "1" + s2 + "2", (s1, s2) -> s1 + "*" + s2);

        System.out.println("拼接字符串str：" + str);
        System.out.println("拼接字符串str1：" + str1);
        System.out.println("拼接字符串str2：" + str2);
        System.out.println("拼接字符串str3：" + str3);

        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0)
                .reduce(Double.valueOf(1), Double::min);
        System.out.println("最小值：" + minValue);

        // 两种写法,结果:求和，sumValue = 20, 有起始值
        int sumValue1 = Stream.of(1, 2, 3, 4)
                .reduce(10, Integer::sum);
        int sumValue2 = Stream.of(1, 2, 3, 4)
                .reduce(10, (v1, v2) -> v1 + v2);

        System.out.println("求和有起始值：" + sumValue1);
        System.out.println("求和有起始值：" + sumValue2);

        // 两种写法,结果:求和，sumValue = 10, 无起始值
        int sumValue3 = Stream.of(1, 2, 3, 4)
                .reduce(Integer::sum).orElse(Integer.valueOf(0));
        int sumValue4 = Stream.of(1, 2, 3, 4)
                .reduce((v1, v2) -> v1 + v2).orElse(Integer.valueOf(0));
        System.out.println("求和无起始值：" + sumValue3);
        System.out.println("求和无起始值：" + sumValue4);
    }
}
