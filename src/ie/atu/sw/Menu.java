package ie.atu.sw;

import java.util.Scanner;

public class Menu {

	private Scanner s;
	private boolean keepRunning = true;

	public Menu() {
		s = new Scanner(System.in);
	}

	// Initialize Parser object
	Parser p = new Parser();

	// Start Menu & Process User Choice
	public void start() {
		showOptions();
		while (keepRunning) {

			// try-catch to deal with non-integer inputs generating NumberFormatExceptions
			try {
				int choice = Integer.parseInt(s.next());

				if (choice == 1) {
					// specify text file directory
					System.out.println("Input Folder Path:");
					// reads an String value
					String inputPath = s.next();

					if (inputPath != null) {
						p.setInputPath(inputPath);
						System.out.print("Select Option [1-5]>");
						System.out.println();
					} else {
						System.out.println("Error");
					}

				} else if (choice == 2) {

					// specify ngram size (1-5)
					System.out.println("Enter N-Gram Size (1-5): ");

					// reads an int value
					int nGramSize = s.nextInt();

					// set n-gram size in Builder class
					if (p.setNGramSize(nGramSize) == true) {
						System.out.println("Success!");
						System.out.print("Select Option [1-5]>");
						System.out.println();
					} else {
						System.out.println("Error: Ensure Size is from 1 to 5");
						System.out.print("Select Option [1-4]>");
						System.out.println();
					}

				} else if (choice == 3) {

					System.out.println("Specify Output file name/type should be .txt");

					// reads an String value
					String outputName = s.next();

					if (outputName != null) {
						p.setOutputName(outputName);
						System.out.print("Select Option [1-5]>");
						System.out.println();
					} else {
						System.out.println("Error");
					}

				} else if (choice == 4) {

					try {
						// main n-gram builder method
						if (p.executeNGramBuilder() == true) {
							System.out.println("Success, please see output file saved in folder.");
						} else {
							System.out.println("There was an error, please ensure all inputs were correct");
						}
						;

					} catch (Exception e) {

						e.printStackTrace();
					}

				} else if (choice == 5) {
					System.out.println("Closing Application..");
					System.exit(0);

				} else {
					System.out.println("ERROR: Select between 1-5");
				}

			} catch (Exception e) {
				System.out.println("Please enter a number between 1 & 5 only. No characters.");
			}
		}
	}

	private void showOptions() {
		System.out.println("************************************************************");
		System.out.println("*      ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*                  N-Gram Frequency Builder                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Text File Directory");
		System.out.println("(2) Specify n-Gram Size");
		System.out.println("(3) Specify Output File Name (.txt)");
		System.out.println("(4) Build n-Grams ");
		System.out.println("(5) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print("Select Option [1-5]>");
		System.out.println();
	}

}
