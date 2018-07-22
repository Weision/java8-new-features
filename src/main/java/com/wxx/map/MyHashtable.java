package com.wxx.map;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MyHashtable {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		myHashtable1();
		myHashtable2();
		myHashtable3();
	}

	/**
	 * entrySet().iterator()
	 */
	private static void myHashtable1() {
		Map map = new Hashtable();
		for (int i = 0; i < 1000000; i++) {
			map.put(i, i);
		}

		Iterator iterator = map.entrySet().iterator();
		long begin = System.currentTimeMillis();
		while (iterator.hasNext()) {
			Entry next = (Entry) iterator.next();
			Object key = next.getKey();
			Object value = next.getValue();
			// System.out.println(">>>>>key>>>>>" + next.getKey() + ">>>>>value>>>" +
			// next.getValue());
		}
		long end = System.currentTimeMillis();
		System.out.println("---myHashtable1 - cost---" + (end - begin));
	}

	/**
	 * keySet()
	 */
	private static void myHashtable2() {
		Map map = new Hashtable();
		for (int i = 0; i < 1000000; i++) {
			map.put(i, i);
		}

		long begin = System.currentTimeMillis();
		for (Object obj : map.keySet()) {
			Object key = obj;
			Object value = map.get(key);
			// System.out.println(">>>>>key>>>>>" + key + ">>>>>value>>>" + map.get(key));
		}
		long end = System.currentTimeMillis();
		System.out.println("---myHashtable2 - cost---" + (end - begin));
	}

	/**
	 * entrySet()
	 */
	private static void myHashtable3() {
		Map map = new Hashtable();
		for (int i = 0; i < 1000000; i++) {
			map.put(i, i);
		}

		long begin = System.currentTimeMillis();
		for (Object object : map.entrySet()) {
			Map.Entry<Integer, Integer> entry = (Entry<Integer, Integer>) object;
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			// System.out.println(">>>>>key>>>>>" + entry.getKey() + ">>>>>value>>>" +
			// entry.getValue());
		}
		long end = System.currentTimeMillis();
		System.out.println("---myHashtable3 - cost---" + (end - begin));
	}
}
