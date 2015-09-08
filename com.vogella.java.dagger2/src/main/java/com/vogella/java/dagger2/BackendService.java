package com.vogella.java.dagger2;

import javax.inject.Inject;

public class BackendService {
	
	@Inject
	public User user;
	
	private String serverUrl;
	
	@Inject
	public BackendService(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	public boolean callServer() {
		if (user !=null && serverUrl!=null && serverUrl.length()>0) {
			System.out.println("User: " + user + " ServerUrl: "  + serverUrl);
			return true;
		}
		return false;
	}
}
