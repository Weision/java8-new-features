package com.wxx.stream.apis;

import com.wxx.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamFlatMap {

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
        //5 flatMap 把Stream中的层级结构扁平化并返回Stream
        //如List中存在List：
        //在获取第一层stream后再将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字
        //Stream可以容纳不同的数据类型,Stream操作（filter，sum，distinct ...）和collectors不支持它，所以我们需要使用flatMap（）进行以下转换
        System.out.println("-------------flatMap 转换集合内集合(其他对象)-------------");
        String[][] arr = new String[][]{{"333", "444"}, {"111", "555"}, {"666", "222"}};
        //打印
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("-------------flatMap 遍历元素value并执行传入的lambda表达式-------------");
        Arrays.stream(arr).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        //聚合操作　filter
        System.out.println("-------------二维数组未使用flatMap过滤等于666的元素-------------");
        Arrays.stream(arr).filter(element -> "666".equals(element)).forEach(System.out::println);
        System.out.println("-------------二维数组使用flatMap过滤等于666的元素-------------");
        Arrays.stream(arr).flatMap(x -> Arrays.stream(x)).filter(element -> "666".equals(element)).forEach(System.out::println);


        //6 flatMapToInt(flatMapToInt/flatMapToLong/flatMapToDouble) 把Stream中的层级结构扁平化并返回Stream
        System.out.println("-------------flatMapToInt 遍历元素value,执行传入的lambda表达式-------------");
        //6.1 将数组元素转换成int型
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5");
        stringList.stream().flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).forEach(System.out::println);
        //6.2 获取元素长度
        System.out.println("-------------flatMapToInt 遍历元素value,执行传入的lambda表达式-------------");
        List<String> stringList1 = Arrays.asList("Geeks", "GFG", "GeeksforGeeks", "gfg");
        stringList1.stream().flatMapToInt(str -> IntStream.of(str.length())).forEach(System.out::println);
    }
}
