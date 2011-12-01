package de.vogella.regex.test;

public class CopyOfRegexTestStrings {
	public static final String EXAMPLE_TEST = "<title> Naselecken </title> Eclipse provides 		<code>Perspectives</code> 		. 		<code>Views</code> 	,	and 		<code>Editors</code> 		. 		<code>Views</code> 		and 		<code>Editors</code> 		are grouped into 		<code>Perspectives</code> 		. All projects are 		located in a 		<code>workspace</code> 		.";

	public static void main(String[] args) {
		// Replace all whitespace with tabs
		String pattern = "(</code>)(\\s+)(\\.)";
		System.out.println(EXAMPLE_TEST.replaceAll(pattern, "</code>$3"));

		pattern = "(?i)(<title.*?>)(.+?)(</title>)";
		String updated = EXAMPLE_TEST.replaceAll(pattern, "$2");
		System.out.println(updated);

	}
}
