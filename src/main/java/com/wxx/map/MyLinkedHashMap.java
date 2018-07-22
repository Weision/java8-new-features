package com.wxx.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MyLinkedHashMap {
	public static void main(String[] args) {
		
	}
	/**
	 * entrySet().iterator()
	 */
	private static void myHashMap1() {
		Map map = new LinkedHashMap();
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
		System.out.println("---myHashMap1 - cost---" + (end - begin));
	}
}
