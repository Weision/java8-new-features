package com.wxx.stream.apis;

import com.wxx.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamForeach {

    private static List<Student> students = new ArrayList<>();
    private static List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666");

    static {
        students.add(Student.builder().age(18).name("Weison").score(90).build());
        students.add(Student.builder().age(19).name("Evan").score(80).build());
        students.add(Student.builder().age(20).name("Jack").score(70).build());
        students.add(Student.builder().age(21).name("Luis").score(60).build());
    }
    public static void main(String[] args) {
        //1 forEach 迭代流中的元素
        System.out.println("-------------forEach 遍历stream中的元素-------------");
        strings.stream().forEach(System.out::println);
        students.stream().forEach(System.out::println);
    }
}
