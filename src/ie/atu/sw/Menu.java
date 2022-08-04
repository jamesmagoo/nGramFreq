package ie.atu.sw;

import java.util.Scanner;

public class Menu {

	private Scanner s;
	private boolean keepRunning = true;

	public Menu() {
		s = new Scanner(System.in);
	}

	nGramBuilder builder = new nGramBuilder();

	// Start Menu & Process User Choice
	public void start() {
		showOptions();
		while (keepRunning) {
			int choice = Integer.parseInt(s.next());

			if (choice == 1) {
				// specify text file directory
				System.out.println("Select Files");
				// JFileChooser here to get folder path
				//showFolderChooser();
				// send folder path to setDirectory
				builder.setDirectory();

			} else if (choice == 2) {
				// specify ngram size (1-5)
				System.out.println("Specify N-Gram Size (Max 5)");
				builder.setNGramSize(3);
			} else if (choice == 3) {
				System.out.println("Specify Output file name/type should be CSV");
			} else if (choice == 4) {
				// build ngrams
				System.out.println("BUILD");
			} else if (choice == 5) {
				// build ngrams
				System.out.println("Quit");
			} else {
				System.out.println("ERROR: Select between 1-5");
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
		System.out.println("(3) Specify Output File");
		System.out.println("(4) Build n-Grams ");
		System.out.println("(5) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print("Select Option [1-4]>");
		System.out.println();
	}

}
