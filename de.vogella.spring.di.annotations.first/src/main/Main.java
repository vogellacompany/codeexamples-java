package main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import testbean.MySpringBeanWithDependency;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/beans.xml");
		BeanFactory factory = context;
		MySpringBeanWithDependency test = (MySpringBeanWithDependency) factory
				.getBean("mySpringBeanWithDependency");
		test.run();
	}
}
