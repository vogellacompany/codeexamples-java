package de.vogella.regex.test;

public class RegexTestStrings {
	public static final String EXAMPLE_TEST = "This is my small example "
			+ "string which I'm going to " + "use for pattern matching.";

	public static void main(String[] args) {
		System.out.println(EXAMPLE_TEST.matches("\\w.*"));
		String[] splitString = (EXAMPLE_TEST.split("\\s+"));
		System.out.println(splitString.length);// Should be 14
		for (String string : splitString) {
			System.out.println(string);
		}
		// Replace all whitespace with tabs
		System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
	}
}
