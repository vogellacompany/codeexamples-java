package de.vogella.eclipse.ide.first;

public class AnnotationTestNavigation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Warning in Eclipse as test is not used
		boolean test = false;

		// Warning in Eclipse as s is not used
		String s = "unused";

	}

	public void testMethod() {
		// Syntax error in Eclipse because of missing
		// semicolumn
		System.out.println("test")
	}
}
