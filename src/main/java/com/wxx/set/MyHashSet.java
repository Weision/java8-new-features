package com.wxx.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet {
	public static void main(String[] args) {
	    System.out.println(16 >>> 16);
		myHashSet1();
		//myHashSet2();
		//myHashSet3();
	}
	/**
	 * 将一个有顺序的数组放进一个HashSet中，再将其遍历打印
	 * 用以验证HashSet的存储是无序的
	 * 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void myHashSet1()
	{
		Set s = new HashSet();
		String[] a = "a,b,c,d,e,f,g".split(",");
		System.out.println(">>myHashSet1>>start");
		for(String aa : a)
			s.add(aa);
		
		Iterator i = s.iterator();
		while(i.hasNext())
			System.out.println(">>>"+i.next());
		System.out.println(">>myHashSet1>>end");
			
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void myHashSet2()
	{
		Set s = new HashSet();
		String[] a = "g,f,e,d,c,b,a".split(",");
		System.out.println(">>myHashSet2>>start");
		for(String aa : a)
			s.add(aa);
		
		Iterator i = s.iterator();
		while(i.hasNext())
			System.out.println(">>>"+i.next());
		System.out.println(">>myHashSet2>>end");
			
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void myHashSet3()
	{
		Set s = new HashSet();
		String[] a = "e,d,c,a,f,b,g".split(",");
		for(String aa : a)
			s.add(aa);
		
		Iterator i = s.iterator();
		System.out.println(">>myHashSet3>>start");
		while(i.hasNext())
			System.out.println(">>>"+i.next());
		System.out.println(">>myHashSet3>>end");
			
	}
}
