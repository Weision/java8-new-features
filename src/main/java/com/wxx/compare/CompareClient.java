package com.wxx.compare;

import com.wxx.mock.MockData;

import java.util.Collections;
import java.util.List;

/**
 * Collections.sort(list);
 * Arrays.sotr(arr)
 */
public class CompareClient {
    public static void main(String[] args) {
        List list = MockData.mockBigdecialList();
        list.stream().forEach(System.out::println);
        //sort方法比较的数组需要是一个实现了Comparable接口的类
        //因为sort方法内部需要用compareTo方法进行两个值得比较
        //使用的是mergesort算法对数组中的元素进行排序
        Collections.sort(list);
        System.out.println("---排序后---");
        list.stream().forEach(System.out::println);
        //Arrays.stream(students).sorted()
        //Integer public static int compare(int x, int y)
        //Double public static int compare(double d1, double d2)
    }
}
