package main;

import javax.xml.ws.Endpoint;

import calculator.Multiply;

public class MyServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Endpoint.publish(
				"http://localhost:8080/WebServiceExample/multiply",
				new Multiply());
	}

}
