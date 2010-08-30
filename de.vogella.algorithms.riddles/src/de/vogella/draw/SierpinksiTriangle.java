package de.vogella.draw;

import edu.princeton.draw.StdDraw;

/**
 * Draw a triangle with each side lenght of 1. From the last drawn point, pick
 * randomly one of the orginal points and draw a line halfway between the
 * orginal point and the new selected point.
 * 
 * @author d034797
 * 
 */
public class SierpinksiTriangle {

	public static void main(String[] args) {
		double[] xValues = { 0, 1, 0.5 };
		double[] yValues = { 0, 0, Math.sqrt(3) / 2 };
		double x = 1;
		double y = 0;
		for (int i = 0; i <= 400000; i++) {
			int select = (int) (Math.random() * 3.0);
			x = (x + xValues[select]) / 2;
			y = (y + yValues[select]) / 2;
			System.out.println("Draw");
			StdDraw.point(x, y);
		}
	}
}
