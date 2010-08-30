package testbean;

import writer.IWriter;

public class MySpringBeanWithDependency {
	private IWriter writer;

	public void setWriter(IWriter writer) {
		this.writer = writer;
	}

	public void run() {
		String s = "This is my test";
		writer.writer(s);
	}
}
