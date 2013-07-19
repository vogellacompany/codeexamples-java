package de.vogella.regex.weblinks;

import java.util.List;

public class TestClass {

	public static void main(String[] args) {
		LinkGetter getter = new LinkGetter();
		List<String> links = getter.getLinks("http://www.vogella.com/articles/JavaRegularExpressions/article.html");
		for (String string : links) {
			System.out.println(string);
		}
	}

}
