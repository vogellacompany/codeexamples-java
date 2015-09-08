package com.vogella.java.dagger2.main;

import com.vogella.java.dagger2.BackendService;
import com.vogella.java.dagger2.component.DaggerMyComponent;
import com.vogella.java.dagger2.component.MyComponent;

public class Main {

	public static void main(String[] args) {
		MyComponent component = DaggerMyComponent.builder().build();
		BackendService createBackendService = component.createBackendService();
		component.injectIntoBackendService(createBackendService);
		boolean callServer = createBackendService.callServer();
		if (callServer) {
			System.out.println("Server call was successful. ");
		} else {
			System.out.println("Server call failed. ");
		}
	}

}
