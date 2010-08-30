package de.vogella.usa;

import java.io.BufferedReader;
import java.io.FileReader;

import edu.princeton.draw.StdDraw;

public class PlotFilter {

	public static void main(String[] args) {
		// Set the coordinates
		double x0 = 669905.0;
		double y0 = 247205.0;
		double x1 = 1244962.0;
		double y1 = 700000.0;
		StdDraw.setXscale(x0, x1);
		StdDraw.setYscale(y0, y1);
		// read in bounding box and rescale
		FileReader file;
		try {
			file = new FileReader("usa.txt");
			BufferedReader reader = new BufferedReader(file);
			String line;
			// plot points, one at a time
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("(\\s)+");
				// System.out.println(split[1]);
				// System.out.println(split[2]);
				double x = Double.parseDouble(split[1]);
				double y = Double.parseDouble(split[2]);
				StdDraw.point(x, y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}