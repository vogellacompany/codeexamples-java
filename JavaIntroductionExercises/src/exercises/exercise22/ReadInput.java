package exercises.exercise22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * This class will read an line from the command line
 * and return it from the caller
 */
public class ReadInput {
	public String readLine() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		// We will later learn want a try / catch block is
		try {
			String userInput = in.readLine();
			return userInput;
		} catch (IOException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	public int readInteger() {
		String in = readLine();
		try {
			// Translate string to integer
			return Integer.parseInt(in);
		} catch (NumberFormatException e) {
			// In case we did not get a number return zero
			return 0;
		}

	}
}
