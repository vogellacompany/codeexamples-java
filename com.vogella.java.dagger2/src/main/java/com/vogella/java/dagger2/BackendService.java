package com.vogella.java.dagger2;

import javax.inject.Inject;

public class BackendService {
	private User user;
	private String serverUrl;
	
	@Inject
	public BackendService(	User user, String serverUrl) {
		this.user = user;
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
