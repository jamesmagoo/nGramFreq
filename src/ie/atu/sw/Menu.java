package ie.atu.sw;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Menu {

	private Scanner s;
	private boolean keepRunning = true;
	private String inputPath;

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

				// Display menu for user to select folder for input
				showSetDirectory();

				// set folder path for builder
				if (builder.setInputPath(inputPath) == true) {
					System.out.println("Success!");
					System.out.print("Select Option [1-4]>");
					System.out.println();
				} else {
					System.out.println("Error");
				}
				;

			} else if (choice == 2) {
				
			    // specify ngram size (1-5)
			    System.out.println("Enter N-Gram Size (1-5): ");

			    // reads an int value
			    int nGramSize = s.nextInt();

			    // set n-gram size in Builder class
			    if(builder.setNGramSize(nGramSize) == true) {
			    	System.out.println("Success!");
					System.out.print("Select Option [1-4]>");
					System.out.println();
			    } else {
			    	System.out.println("Error: Ensure Size is from 1 to 5");
			    	System.out.print("Select Option [1-4]>");
					System.out.println();
			    }
			    

			} else if (choice == 3) {

				System.out.println("Specify Output file name/type should be CSV");

			} else if (choice == 4) {

				builder.processNGrams(inputPath);
//				if (inputPath != null) {
//
//				} else {
//					System.out.println("Ensure you have set input & output locations, using Option 1 & 3");
//					System.out.print("Select Option [1-4]>");
//				}

			} else if (choice == 5) {
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

	/**
	 * Display GUI for user to set the input directory path
	 * 
	 * @param null
	 * @return void
	 *
	 */
	private void showSetDirectory() {
		JFileChooser chooser = new JFileChooser(new java.io.File("."));
		chooser.setDialogTitle("Select Folder For N-Gram Builder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			inputPath = chooser.getSelectedFile().getPath();

		} else {
			System.out.println("No Selection ");
		}
	}



}
