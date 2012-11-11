package com.vogella.annotations;

import java.lang.reflect.Method;

public class AnnotationRunner {

	public static void main(String[] args) {

		AnnotationRunner runner = new AnnotationRunner();
		Method[] methods = runner.getClass().getMethods();

		for (Method method : methods) {
			CanRun annos = method.getAnnotation(CanRun.class);
			if (annos != null) {
				try {
					method.invoke(runner);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void method1() {
		System.out.println("method1");
	}

	@CanRun
	public void method2() {
		System.out.println("method2");
	}

	@CanRun
	public void method3() {
		System.out.println("method3");
	}

	public void method4() {
		System.out.println("method4");
	}

	public void method5() {
		System.out.println("method5");
	}

}