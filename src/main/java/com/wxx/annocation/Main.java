package com.wxx.annocation;

public class Main {
	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.say();
		hero.speak();

		boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);

		if (hasAnnotation) {
			TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);

			System.out.println("id:" + testAnnotation.id());
			System.out.println("msg:" + testAnnotation.msg());
		}
	}
}
