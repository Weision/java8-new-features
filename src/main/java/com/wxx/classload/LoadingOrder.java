package com.wxx.classload;

/**
 * 父类子类的加载顺序
 */
public class LoadingOrder {
	public static void main(String[] args) throws Exception {
		B b = new B();
		// Class.forName("stringTest.B"); // 执行静态代码块和为静态属性初始化值
		// ClassLoader.getSystemClassLoader().loadClass("stringTest.B"); // 不执行，只加载类
	}
}

class A {
	// 静态代码块和静态属性按顺序执行（类加载阶段）
	static int staticNum = staticMethod();

	static {
		System.out.println("A静态代码块");
	}

	// 非静态属性初始化值和非静态代码块顺序执行（创建对象阶段）
	int num = method();

	{
		System.out.println("A代码块");
	}

	// 最后才调用构造方法
	A() {
		System.out.println("A构造方法");
	}

	static int staticMethod() {
		System.out.println("A调用静态方法为静态属性初始化值");
		return 5;
	}

	int method() {
		System.out.println("A调用普通方法为属性初始化值");
		return 5;
	}

}

class B extends A {
	static int num2 = staticMethod1();

	static {
		System.out.println("B静态代码块");
	}

	static int staticMethod1() {
		System.out.println("B调用静态方法为静态属性初始化值");
		return 5;
	}

	{
		System.out.println("B代码块");
	}

	B() {
		System.out.println("B构造方法");
	}
	
	
}
