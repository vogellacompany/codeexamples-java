package com.vogella.java.retrofitgerrit;

public class StartUp {

	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		Controller controller = new Controller(userInterface);
		userInterface.setController(controller);
		userInterface.initUi();
	}
}
