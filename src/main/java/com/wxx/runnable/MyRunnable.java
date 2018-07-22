package com.wxx.runnable;

import java.util.Map;

public class MyRunnable implements Runnable {

	private int i;
	private Map map;

	MyRunnable(Map map) {
		this.map = map;

	}

	public void run() {
		for (i = 0; i < 30; i++) {
			map.put(i, i);
			System.out.println(Thread.currentThread().getName() + "add" + i);
		}
		System.out.println(Thread.currentThread().getName()+">>end>>"+map);
	}

}
