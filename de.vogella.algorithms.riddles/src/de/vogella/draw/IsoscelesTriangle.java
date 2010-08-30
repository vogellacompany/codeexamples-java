package de.vogella.draw;

import edu.princeton.draw.StdDraw;

/**
 * The function draw creates a isosceles Triangle (gleichschneckliges Dreieck)
 * with the lenght n
 * 
 * @author Lars Vogel
 * 
 */
public class IsoscelesTriangle {
	public static void draw(double length) {
		StdDraw.setXscale(0, length);
		StdDraw.setYscale(0, length);
		StdDraw.line(0, 0, length, 0);
		// Satz des Pythagoras c^2 = a^2+b^2 -> b = sqrt(c^2 - a^2)
		double y = Math.sqrt(Math.pow(length, 2) - Math.pow(length / 2, 2));
		StdDraw.line(length, 0, length / 2, y);
		StdDraw.line(length / 2, y, 0, 0);

	}

	public static void main(String[] args) {
		draw(10);
	}
}
