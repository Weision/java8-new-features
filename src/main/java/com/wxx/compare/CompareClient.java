package com.wxx.compare;

import java.util.Arrays;

public class CompareClient {
    public static void main(String[] args) {
        Student[] students = {new Student("sa", 10), new Student("sb", 7), new Student("sc", 9)};
        System.out.println("---排序前---");
        for (Student student : students) {
            System.out.println(student.toString());
        }
        //sort方法比较的数组需要是一个实现了Comparable接口的类
        //因为sort方法内部需要用compareTo方法进行两个值得比较
        //使用的是mergesort算法对数组中的元素进行排序
        Arrays.sort(students);
        System.out.println("---排序后---");
        for (Student student : students) {
            System.out.println(student.toString());
        }

        //Arrays.stream(students).sorted()
        //Integer public static int compare(int x, int y)
        //Double public static int compare(double d1, double d2)
    }
}
