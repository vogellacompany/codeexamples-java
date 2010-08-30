package main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.Test;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/beans.xml");
		BeanFactory factory = (BeanFactory) context;
		Test test = (Test) factory.getBean("test");
		test.run();
	}
}
