package exercises.exercise28;

public class MainTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyFile file = new MyFile();
		String s = file.readTextFile("c:\\temp\\person.txt");
		String lines[] = s.split("\n");
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
			String names[] = lines[i].split(",");
			System.out.println(names[0]);
			System.out.println(names[1]);
		}
	}
}
