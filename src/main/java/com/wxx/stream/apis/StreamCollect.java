package com.wxx.stream.apis;

import com.wxx.domain.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * collect 归约操作,流中的元素累积成一个汇总结果
 */
public class StreamCollect {
    private static List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666", null);
    private static List<Integer> integers = Arrays.asList(555, 333, 444, 111, 222, 666, null);
    private static List<Student> classOneStudents = new ArrayList<>();
    private static List<Student> classTwoStudents = new ArrayList<>();
    private static List<Student> classThreeStudents = new ArrayList<>();
    private static List<List<Student>> students = new ArrayList<>();

    static {
        classOneStudents.add(Student.builder().age(18).name("Weison").score(90).build());
        classOneStudents.add(Student.builder().age(19).name("Evan").score(80).build());
        classOneStudents.add(Student.builder().age(20).name("Jack").score(70).build());
        classOneStudents.add(Student.builder().age(21).name("Luis").score(60).build());

        classTwoStudents.add(Student.builder().age(19).name("Elen").score(80).build());
        classTwoStudents.add(Student.builder().age(20).name("Obam").score(70).build());
        classTwoStudents.add(Student.builder().age(21).name("Bush").score(60).build());

        classThreeStudents.add(Student.builder().age(20).name("Jackson").score(70).build());
        classThreeStudents.add(Student.builder().age(21).name("Linn").score(60).build());
        students.add(classOneStudents);
        students.add(classTwoStudents);
        students.add(classThreeStudents);
    }

    public static void main(String[] args) {
        // 具体的做法是通过定义新的Collector接口来定义的
        System.out.println("-------------collect 汇总结果-------------");
        //13.1 toList toSet toMap
        List<String> list = strings.stream()
                .filter(element -> null != element)
                .collect(Collectors.toList());
        System.out.println("汇总结果list-->" + list);

        Set<String> set = strings.stream()
                .filter(element -> null != element)
                .collect(Collectors.toSet());
        System.out.println("汇总结果set-->" + set);

        Map<String, String> map = strings.stream()
                .filter(element -> null != element)
                .collect(Collectors.toMap(x -> x, x -> x + "1"));
        System.out.println("汇总结果map-->" + map);

        //13.2 joining  只适合字符串
        String joining = strings.stream().collect(Collectors.joining(","));
        System.out.println("collect joining-->" + joining);

        //13.3 counting
        Long counting = strings.stream().collect(Collectors.counting());
        System.out.println("collect counting-->" + counting);

        //13.4 maxBy groupingBy
        Integer integer = integers.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(o -> o))).get();
        System.out.println("collect integer-->" + integer);

    }
}
