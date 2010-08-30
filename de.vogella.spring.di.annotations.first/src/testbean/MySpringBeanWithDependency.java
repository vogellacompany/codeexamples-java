package testbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import writer.IWriter;

@Service
public class MySpringBeanWithDependency {
	private IWriter writer;

	@Autowired
	public void setWriter(IWriter writer) {
		this.writer = writer;
	}

	public void run() {
		String s = "This is my test";
		writer.writer(s);
	}
}
