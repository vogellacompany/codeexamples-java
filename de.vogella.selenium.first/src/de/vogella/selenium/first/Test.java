package de.vogella.selenium.first;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Test {
	public static void main(String[] args) {
		Selenium driver = new DefaultSelenium("localhost", 4444, "*firefox",
				"http://localhost:4444/wd/hub");

		driver.start();
		// go to web pages and do stuff...
		driver.open("http://www.vogella.de");
		driver.captureScreenshot("\\test.png");

		driver.stop();

	}
}
