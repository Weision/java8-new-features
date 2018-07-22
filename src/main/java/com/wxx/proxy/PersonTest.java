package com.wxx.proxy;

public class PersonTest {
	public static void main(String[] args) {
		
		//静态代理
		 IPerson proxy = new PersonProxy(new Person());

         proxy.eating();

         proxy.sleep();
		
       //动态代理
		IPerson person = (IPerson) DynaProxyFactory.getProxy(new Person());

		// 返回代理类,代理类是JVM在内存中动态创建的,该类实现传入的接口数组的全部接口(的全部方法).

		person.eating();

		person.sleep();

	}
}
