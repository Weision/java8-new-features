package com.wxx.runnable;

import java.util.HashMap;
import java.util.Map;

public class MyRunnable2 implements Runnable {

	private int i;
	private Map map;

	MyRunnable2(Map map) {
		this.map = map;

	}

	public void run() {
		for (i = 30; i < 60; i++) {
			map.put(i, i);
			System.out.println(Thread.currentThread().getName() + "add" + i);
		}
		System.out.println(Thread.currentThread().getName() + ">>end>>" + map);
	}

}
