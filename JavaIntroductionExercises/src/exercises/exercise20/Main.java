package exercises.exercise20;

// We will later learn want a import mean
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		String userInput = "";
		Random random = new Random();
		// Create a number between 0 and 100
		int nextInt = random.nextInt(101);
		ReadInput in = new ReadInput();
		System.out.println("Do you think the number is larger then 50?");
		userInput = in.readLine();
		if (userInput.equalsIgnoreCase("y") && nextInt > 50) {
			System.out.println("Very well, you did it!");
		} else {
			System.out.println("You are a loooooser!");
		}
		System.out.println("The number was " + nextInt);
	}
}
