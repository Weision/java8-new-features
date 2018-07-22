package com.wxx.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] a) {
		App view = new App();
		// 轻量级集合包装器Arrays和Collections ;
		 //view.viewOper();
		// 子范围视图
		//view.viewSub();
		// 不可修改视图
		view.viewUnmodif();
		// 同步视图
		//view.viewSyn();
		// 被检验视图
		//view.viewCheck();
		// 可选操作的说明
		// view.
	}
	public void viewOper() {

		String[] temp = new String[3];
		// 返回一个视图对象，你只能使用get(),set()方法访问底层的数组，而任何尝试
		// 改变数组大小的方法都抛出UnsupportedOperationException。
		List<String> tempList = Arrays.asList("aa", "bb", "cc");
		System.out.println("01 = " + tempList.get(0));
		System.out.println("02 = " + tempList.set(1, "ddd"));
		System.out.println("03 = " + tempList.get(1));

		// 返回一个实现了list接口不可修改的对象；
		List<String> settings = Collections.nCopies(5, "Baby");
		List<String> settings2 = new ArrayList<String>();
		 settings2.add("B");
		String testStr0 = settings.get(0);
		String testStr1 = settings.get(1);
		System.out.println(testStr1 == testStr0);// 返回true
		System.out.println(testStr0 + "   " + testStr1);
		System.out.println("settings01 =" + settings);
		// 抛出UnsupportedOperationException
		// System.out.println("set01 =" + settings.set(1, "bbb"));
		// 抛出UnsupportedOperationException
		 //settings.add("B");
		System.out.println("settings02 =" + settings);
	}

	/*
	 * 可以为许多集合建立子范围视图！任何操作 作用于子范围，他们都能通过在这个集合中反映出来！
	 */
	public void viewSub() {
		List<String> tempList = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {

			tempList.add("" + i);

		}
		// 返回子范围视图；
		List subList = tempList.subList(3, 7);

		System.out.println(subList);// [3, 4, 5, 6]

		System.out.println("01 = " + subList.get(0));
		System.out.println("02 = " + subList.set(0, 33));
		System.out.println(subList);
		subList.clear();
		System.out.println(tempList);// [0, 1, 2, 7, 8, 9]

	}

	/*
	 * 如果你想查看代码的某个部分但不修改某个集合的内容可以考虑以下方法; 注：不可修改视图不是本身不可修改，通过集合的原始引用，
	 * 我们仍然能够做集合想做的事情。视图只包装了接口而不是实际的对象。
	 */
	public void viewUnmodif() {
		// Collections.unmodifiableCollection()
		// Collections.unmodifiableList()
		// Collections.unmodifiableMap()
		// Collections.unmodifiableSet()
		// Collections.unmodifiableSortedSet()
		// Collections.unmodifiableSortedMap()

		List<String> tempList = new LinkedList();
		for (int i = 0; i < 10; i++) {
			tempList.add("" + i);
		}
		// 返回不可修改视图
		List<String> unmodifiableList = Collections.unmodifiableList(tempList);
		String temp = unmodifiableList.get(1);
		System.out.println("01 = " + tempList);

		tempList.add("baby");
		System.out.println("02 = " + tempList);

		//unmodifiableList.add("bb");
		// 抛出UnsupportedOperationException
		System.out.println("03 = " + tempList);
		System.out.println("04 = " + unmodifiableList);
		tempList.remove(0);
		System.out.println("05 = " + unmodifiableList);
	}

	public void viewSyn() {
		// 多线程来访问一个集合！我们能够想象会发生什么。
		// 一个线程试图将一个元素添加到散列表中，而另一个线程或者正在遍
		// 历，或者对元素重新散列！或者......
		// Java的设计者没有实现“安全集合类”，而是使用视图机制来保证集合线程的安全！
		// HashMap<String,String> hashMap=new HashMap<String,String>();
		// Map<String,String> map=Collections.synchronizedMap(hashMap);
		// map引用将会使得一切恢复正常！不必担心同步的问题！但是这个包装器的作用有限：
		// 推荐使用：java.util.concurrent中定义的集合类！

	}

	public void viewCheck() {

	}

	
}
