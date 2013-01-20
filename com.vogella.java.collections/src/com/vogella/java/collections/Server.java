package com.vogella.java.collections;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private String url;

	public Server(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String[] args) {
		List<Server> list = new ArrayList<Server>();
		list.add(new Server("http://www.vogella.com"));
		list.add(new Server("http://www.google.com"));
		list.add(new Server("http://www.heise.de"));
	}
}
