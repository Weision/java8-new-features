package com.wxx.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;

public class ParallelStreamClient {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.out.println("-------------stream 10000000---------------");
        long st = System.currentTimeMillis();
        //list.stream().forEach(out::println);
        list.stream().map(element->element+"");
        long so = System.currentTimeMillis();
        System.out.println("-------------stream cost: "+(so-st));
        System.out.println("----------parallelStream 10000000----------");
        //并行处理　执行结果是乱序的
        long st1 = System.currentTimeMillis();
        //list.parallelStream().forEach(out::println);
        list.parallelStream().map(element->element+"");
        long so1 = System.currentTimeMillis();
        System.out.println("-------------parallelStream cost: "+(so1-st1));
        //out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");

    }

    public static void ps() throws InterruptedException {
        out.println("Hello World!");
        // 构造一个10000个元素的集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 并行执行
        list.parallelStream().forEach(integer -> {
            Thread thread = Thread.currentThread();
            // System.out.println(thread);
            // 统计并行执行list的线程
            threadSet.add(thread);
        });
        out.println("threadSet一共有" + threadSet.size() + "个线程");
        out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
            list2.add(i);
        }
        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                // System.out.println("list1" + thread);
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            list2.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                // System.out.println("list2" + thread);
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });

        threadA.start();
        threadB.start();
        countDownLatch.await();
        out.print("threadSetTwo一共有" + threadSetTwo.size() + "个线程");

        out.println("---------------------------");
        out.println(threadSet);
        out.println(threadSetTwo);
        out.println("---------------------------");
        threadSetTwo.addAll(threadSet);
        out.println(threadSetTwo);
        out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
        out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
    }
}
