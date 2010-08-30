package de.vogella.jsf.starter.model;

import java.util.Random;

public class Card {
	private int left;
	private int right;
	private int result = 0; 

	public Card() {
		Random random = new Random();
		int i = 0;
		int j = 0;
		do {
			i = random.nextInt(10);
		} while (i <= 4);

		do {
			j = random.nextInt(100);
		} while (j <= 20);

		left = i;
		right = j;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	// Controller

	public String show() {
		result = left * right; 
		return "success";
	}
	
	public String clear() {
		result = 0; 
		return "clear";
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
