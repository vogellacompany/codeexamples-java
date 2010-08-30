package exercises.exercise22;

import java.util.Random;

public class GuessNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		// Create a number between 0 and 100
		int nextInt = random.nextInt(101);
		boolean gotIt = false;
		ReadInput in = new ReadInput();
		System.out.println("Please guess a number: ");
		while (!gotIt) {
			int userInput = in.readInteger();
			if (nextInt == userInput) {
				System.out.println("You got it!");
				gotIt = true;
			} else {
				if (nextInt < userInput) {
					System.out.println("You guessed " + userInput
							+ " Try again. Tip: Smaller is good.");
				} else {
					if (nextInt > userInput) {
						System.out.println("You guessed " + userInput
								+ " Try again. Tip: Larger is good.");
					}
				}

			}
		}
	}
}
