package com.wxx.stream.apis;

import com.wxx.domain.Student;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupBy {
    public static void main(String[] args) {
        //13.3 maxBy counting groupingBy
        List<Student> students = new ArrayList();

        students.add(new Student("sa", 10, "女", 86, 2));
        students.add(new Student("sb", 7, "男", 58, 1));
        students.add(new Student("sc", 9, "女", 91, 3));
        students.add(new Student("sd", 11, "男", 68, 1));
        students.add(new Student("se", 5, "男", 74, 1));
        students.add(new Student("sf", 12, "女", 59, 1));
        students.add(new Student("sg", 7, "男", 52, 1));


        Map<String, IntSummaryStatistics> collect = students.stream().filter(student -> student.getAge() > 8)
                .collect(Collectors.groupingBy(Student::getSex, Collectors.summarizingInt(Student::getCitationCount)));

        Map<String, List<Student>> collect1 = students.stream()
                .collect(Collectors.groupingBy(Student::getSex));
        collect1.values().stream().forEach(System.out::println);
    }
}
