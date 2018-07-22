package com.wxx.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MyTreeMap {

	public static void main(String[] args) {
		myTreeMap();
	}

	/**
	 * entrySet().iterator()
	 */
	private static void myTreeMap() {
		Map map = new TreeMap();
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
		System.out.println("---myTreeMap - cost---" + (end - begin));
	}
}
