package com.vogella.java.dagger2.main;

import com.vogella.java.dagger2.BackendService;
import com.vogella.java.dagger2.component.DaggerMyComponent;

public class Main {

	public static void main(String[] args) {

		BackendService createBackendService = DaggerMyComponent.create().createBackendService();
		boolean callServer = createBackendService.callServer();
		if (callServer) {
			System.out.println("Server call was successful. ");
		} else {
			System.out.println("Server call failed. ");
		}
	}

}
