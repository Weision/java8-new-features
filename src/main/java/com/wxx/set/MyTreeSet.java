package com.wxx.set;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MyTreeSet {

	public static void main(String[] args) {
		treeSet();
	}
	private static void treeSet()
	{
		Set s = new TreeSet();
		String[] a = "d,c,g,f,a,b,e".split(",");
		for(String aa:a)
			s.add(aa);
		Iterator i = s.iterator();
		while(i.hasNext())
			System.out.println(">>>>"+i.next());
	}
	private static void treeSet1()
	{
		Set s = new TreeSet();
		String[] a = "d,c,g,f,a,b,e".split(",");
		for(String aa:a)
			s.add(aa);
		
		for(int i = 0 ;i<s.size();i++)
		{
			Object[] array = s.toArray();
			System.out.println("////"+ array[i]);
		}
	}
	}
