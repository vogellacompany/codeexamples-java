package de.vogella.web.get;

import java.io.IOException;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String s = "http://www.vogella.de";
		TinyURL tiny = new TinyURL();
		System.out.println(tiny.shorter(s));
		Trim trim= new Trim ();
		System.out.println(trim.shorter(s));
	}

}
