package com.wxx.proxy;

import com.sun.istack.internal.logging.Logger;

public class PersonProxy implements IPerson {
	private IPerson person;

	private Logger logger = Logger.getLogger(PersonProxy.class);

	public PersonProxy(IPerson person) {

		this.person = person;

	}

	public void eating() {

		logger.info("开始执行时间:“ + new Date()");

		person.eating();

		logger.info("“执行结束时间:” + new Date()");

	}

	public void sleep() {

		logger.info("开始执行时间:“ + new Date()");

		person.sleep();

		logger.info("“执行结束时间:” + new Date()");

	}
}
