package com.wxx.stream.apis;

import com.wxx.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Map 主要用来转换stream中的元素 入参为Funtion的lambda
 */
public class StreamMap {
    private static List<Student> students = new ArrayList<>();
    private static List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");

    static {
        students.add(Student.builder().age(18).name("Weison").score(90).build());
        students.add(Student.builder().age(19).name("Evan").score(80).build());
        students.add(Student.builder().age(20).name("Jack").score(70).build());
        students.add(Student.builder().age(21).name("Luis").score(60).build());
    }

    public static void main(String[] args) {
        map();
        mapBySteps();

    }

    private static void map() {
        // 1 转换某元素的值，并返回原Collect
        students.stream().map(student -> mapScore())
                .forEach(System.out::println);
        // 2 取出对象某值 成一个新的Collect
        students.stream().map(student -> student.getScore() + 10)
                .forEach(System.out::println);
    }

    private static Function<Student, Student> mapScore() {
        return student -> {
            student.setScore(student.getScore() + 10);
            return student;
        };
    }

    private static void mapBySteps() {
        // --1 转换数组中元素内容
        // stream() 为strings创建串行流
        Stream<String> stream = strings.stream();

        //3 map 遍历每个元素的value,对其执行传入的lambda表达式后返回Stream
        System.out.println("-------------map 遍历元素value并执行传入的lambda表达式-------------");
        //完整的使用方法：strings.stream().map(i -> i + "@@!").forEach(System.out::println);
        //拆分成两步：１ 遍历元素value并执行传入的lambda表达式
        Stream<String> stringStream = strings.stream().map(i -> i + "@@!");
        //拆分成两步：2 遍历stream并打印元素
        stringStream.forEach(System.out::println);


        //4 mapToInt(mapToInt/mapToLong/mapToDouble同) 遍历每个元素的value,对其元素类型进行转换
        System.out.println("-------------mapToInt 遍历元素value并执行int转换lambda表达式-------------");
        //完整的使用方法：strings.stream().mapToInt(element -> Integer.valueOf(element)).forEach(System.out::println);
        //拆分成两步：１ 遍历元素value并执行传入的类型转换lambda表达式
        IntStream intStream = strings.stream().mapToInt(element -> Integer.valueOf(element));
        //拆分成两步：2 遍历intStream并打印元素
        intStream.forEach(System.out::println);
    }
}
