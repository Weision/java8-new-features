package com.weison.stream.apis;

import com.weison.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamFilter {
    private static List<Student> students = new ArrayList<>();
    private static List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666", null, null);

    static {
        students.add(Student.builder().age(18).name("Weison").score(90).build());
        students.add(Student.builder().age(19).name("Evan").score(80).build());
        students.add(Student.builder().age(20).name("Jack").score(70).build());
        students.add(Student.builder().age(21).name("Luis").score(60).build());
        students.add(null);
    }

    public static void main(String[] args) {

        strings.stream().filter(string -> !"666".equals(string))
                .forEach(System.out::println);

        long count = strings.stream().filter(string -> string != null)
                .filter(string -> !string.isEmpty()).count();
        System.out.println("stream中有[" + count + "]个非空数据!");


        //找出成绩大于60的童鞋
        students.stream().filter(student -> student != null)
                .filter(student -> student.getScore() > 60)
                .forEach(System.out::println);
    }
}
