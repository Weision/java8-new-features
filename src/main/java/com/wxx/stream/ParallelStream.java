package com.wxx.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ParallelStream {
    public static void main(String[] args) throws InterruptedException {
        //order();
        performance();
        //principle();

    }

    public static void order() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        System.out.println("----------stream 结果和结果有序----------");
        list.stream().forEach(out::println);
        System.out.println("----------parallelStream 执行顺序----------");
        //并行处理　执行是乱序的
        list.parallelStream().forEach(out::println);
        List<Integer> collect = list.parallelStream()
                .collect(Collectors.toList());
        System.out.println("----------parallelStream1 结果顺序----------");
        //并行处理　结果是有序的
        collect.forEach(out::println);
    }

    public static void performance() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(i);
        }
        System.out.println("-------------stream 10000000---------------");
        long st = System.currentTimeMillis();
        //list.stream().forEach(out::println);
        list.stream().map(element -> element + "");
        long so = System.currentTimeMillis();
        System.out.println("-------------stream cost: " + (so - st));
        System.out.println("----------parallelStream 10000000----------");
        //并行处理　执行结果是乱序的
        long st1 = System.currentTimeMillis();
        //list.parallelStream().forEach(out::println);
        list.parallelStream().map(element -> element + "");
        long so1 = System.currentTimeMillis();
        System.out.println("-------------parallelStream cost: " + (so1 - st1));
    }

    public static void principle() throws InterruptedException {

        // 构造三个100000个元素的集合
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
            list1.add(i);
            list2.add(i);
        }

        // 1 统计并行执行list的线程
        // 1.1 list并行流操作线程集合threadSet
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 1.2 并行遍历list,并获取当前执行线程
        list.parallelStream().forEach(integer -> {
            // 1.3 获取并统计并行执行list的线程
            Thread thread = Thread.currentThread();
            threadSet.add(thread);
        });

        System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
        System.out.println("threadSet一共有" + threadSet.size() + "个线程");

        // 2 统计并行执行list1的线程
        // 2.1 list并行流操作线程集合threadSetTwo
        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
        // 2.2 限制同时只能执行两个并行任务
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // 2.3 遍历list1,并获取当前执行线程
        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            //遍历完后计数器减一
            countDownLatch.countDown();
        });
        // 2.4 遍历list1,并获取当前执行线程
        Thread threadB = new Thread(() -> {
            list2.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            //遍历完后计数器减一
            countDownLatch.countDown();
        });

        threadA.start();
        threadB.start();
        //通知其他线程执行
        countDownLatch.await();
        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");

        System.out.println("---------------------------");
        System.out.println("threadSet：" + threadSet);
        System.out.println("threadSetTwo：" + threadSetTwo);
        System.out.println("---------------------------");
        threadSetTwo.addAll(threadSet);
        System.out.println("threadSet合并后：" + threadSetTwo);
        System.out.println("threadSetTwo合并后一共有" + threadSetTwo.size() + "个线程");
    }


}
