package com.weison.stream.apis;

import com.weison.domain.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * collect 归约操作,流中的元素累积成一个汇总结果
 */
public class StreamCollect {

    private static List<String> strings = Arrays.asList("555", "333", "444", "111", "222", "666", "555", null);
    private static List<Integer> integers = Arrays.asList(555, 333, 444, 111, 222, 666);
    private static List<Student> classOneStudents = new ArrayList<>();
    private static List<Student> classTwoStudents = new ArrayList<>();
    private static List<Student> classThreeStudents = new ArrayList<>();
    private static List<List<Student>> students = new ArrayList<>();

    static {
        classOneStudents.add(Student.builder().age(5).name("Weison").sex("男").score(90).citationCount(1).build());
        classOneStudents.add(Student.builder().age(6).name("Evan").sex("女").score(80).citationCount(2).build());
        classOneStudents.add(Student.builder().age(7).name("Jack").sex("男").score(70).citationCount(2).build());
        classOneStudents.add(Student.builder().age(8).name("Luis").sex("女").score(60).citationCount(4).build());

        classTwoStudents.add(Student.builder().age(9).name("Elen").sex("女").score(80).citationCount(2).build());
        classTwoStudents.add(Student.builder().age(10).name("Obam").sex("男").score(70).citationCount(5).build());
        classTwoStudents.add(Student.builder().age(11).name("Bush").sex("女").score(60).citationCount(3).build());

        classThreeStudents.add(Student.builder().age(12).name("Jackson").sex("男").score(70).citationCount(1).build());
        classThreeStudents.add(Student.builder().age(13).name("Linn").sex("女").score(60).citationCount(1).build());
        students.add(classOneStudents);
        students.add(classTwoStudents);
        students.add(classThreeStudents);
    }

    public static void main(String[] args) {
        // 具体的做法是通过定义新的Collector接口来定义的
        //1 toList toSet toMap
        List<String> list = strings.stream()
                .filter(element -> null != element)
                .collect(Collectors.toList());
        //2 toSet

        Set<String> set = strings.stream()
                .filter(element -> null != element)
                .collect(Collectors.toSet());

        //3 toMap
        // 不添加mergeFucntion 会报 java.lang.IllegalStateException: Duplicate key 5551
        Map<String, String> stringsMap =
                strings.stream()
                        .filter(element -> null != element)
                        .collect(Collectors.toMap(stringsValue -> stringsValue,
                                stringsValue -> stringsValue + "1",
                                (stringsValue1, stringsValue2) -> stringsValue2));
        System.out.println("stringsMap-->" + stringsMap);

        Map<String, Student> studentMap =
                classOneStudents.stream()
                        .collect(Collectors.toMap(student -> student.getName(),
                                Function.identity(),
                                (student1, student2) -> student2));
        System.out.println("studentMap-->" + studentMap);

        //4 joining  只适合字符串
        //joining = 555,333,444,111,222,666,555,null
        String joining = strings.stream().collect(Collectors.joining(","));

        //5 counting
        //counting = 8
        Long counting = strings.stream().collect(Collectors.counting());

        //6 maxBy
        // integer = 666
        Integer integer = integers.stream()
                .collect(Collectors.maxBy((integer1, integer2) -> integer1 > integer2 ? 1 : -1))
                .orElse(Integer.MIN_VALUE);
        System.out.println("collect integer-->" + integer);

        //7 groupingBy
        //7.1 groupingBy 只分组
        Map<String, List<Student>> allStudentMap = students.stream()
                .flatMap(student -> student.stream())
                .collect(Collectors.groupingBy(Student::getSex));
        System.out.println("allStudentMap--->" + allStudentMap);

        //7.2 groupingBy 分组并计算Student.score的 最大最小平均值
        //{女=IntSummaryStatistics{count=5, sum=340, min=60, average=68.000000, max=80},
        // 男=IntSummaryStatistics{count=4, sum=300, min=70, average=75.000000, max=90}}
        Map<String, IntSummaryStatistics> intSummaryStatisticsMap = students.stream()
                .flatMap(student -> student.stream())
                .collect(Collectors.groupingBy(Student::getSex, Collectors.summarizingInt(student -> student.getScore())));
        System.out.println("intSummaryStatisticsMap--->" + intSummaryStatisticsMap);

        //7.3 groupingBy 分组并求每组的数量
        // {女=5, 男=4}
        Map<String, Long> stringLongMap = students.stream()
                .flatMap(student -> student.stream())
                .collect(Collectors.groupingBy(Student::getSex, Collectors.counting()));
        System.out.println("stringLongMap--->" + stringLongMap);

        //7.4 groupingBy 分组并求男生和女生的奖状总数  要求返回格式为Student
        // {女=5, 男=4}
        List<Student> studentList = students.stream()
                .flatMap(student -> student.stream())
                .filter(student -> student.getAge() > 8)
                .collect(Collectors.groupingBy(Student::getSex))
                .values().stream()
                .map(students -> {
                    Student studentResult = new Student();
                    studentResult.setCitationCount(students.stream().map(student -> student.getCitationCount())
                            .reduce((citation1, citation2) -> citation1 + citation2).orElse(0));
                    studentResult.setSex(students.get(0).getSex());
                    return studentResult;

                })
                .collect(Collectors.toList());
        System.out.println("studentList--->" + studentList);


    }
}

