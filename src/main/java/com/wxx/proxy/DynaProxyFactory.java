package com.wxx.proxy;

import java.lang.reflect.Proxy;

public class DynaProxyFactory {
	// obj为被代理对象

	public static Object getProxy(Object obj) {

		DynaProxyHandler handler = new DynaProxyHandler();

		handler.setTarget(obj);

		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);

	}
}
