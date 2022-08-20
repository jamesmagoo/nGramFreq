package ie.atu.sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {

	// table to store n-gram frequencies
	private Object[][] freqTable;
	// store the nGram size as specified by the user
	private int nGramSize = 0;
	// store the path to the directory for processing as set by user
	private String inputDirPath = null;
	// store the path to the directory for processing as set by user
	private String outputName = "Results.txt";

	public Parser() {
		// TODO: Set this using the n-gram size (n) i.e. 26^n is the max amount of rows
		// needed for an n-gram
		freqTable = new Object[11881376][2];
	}

	/**
	 * Execute the n-gram builder
	 * 
	 * @param void
	 * @return boolean - to provide user feedback
	 *
	 */
	public boolean executeNGramBuilder() {
		// check that size, output path, and input directory path are all specified
		if ((inputDirPath != null) && (nGramSize != 0)) {
			// process the directory
			parseDirectory();
			// Output results to file
			printTable();
			return true;
		} else {
			System.out.println("ERROR: Specify N-Gram Size & Input/Output Directory Paths");
			return false;
		}

	}

	private void printTable() {
		try (FileWriter fw = new FileWriter(new File(outputName)); BufferedWriter bw = new BufferedWriter(fw)) {
			for (int i = 0; i < freqTable.length; i++) {
				// only print not null rows
				if (freqTable[i][0] != null) {
					bw.write(freqTable[i][0].toString());
					bw.append(",");
					bw.write(freqTable[i][1].toString());
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Add the n-gram to the frequency table
	 * 
	 * @param nGram - the n-gram
	 * @return void
	 *
	 */
	public void addNGram(String nGram) {
		int index = nGram.hashCode() % freqTable.length;

		long counter = 1;
		if (freqTable[index][1] != null) {
			counter += (Long) freqTable[index][1];
		}

		freqTable[index][0] = nGram;
		freqTable[index][1] = counter;

	}

	/**
	 * Set the n-gram size for processing
	 * 
	 * @param n - the n-gram size (max 5)
	 * @return boolean
	 *
	 */
	public boolean setNGramSize(int n) {
		// check that input is within allowed range
		if (n == 0 || n > 5) {
			return false;
		} else {
			nGramSize = n;
			System.out.println("N-Gram Size Set to :" + nGramSize);
			return true;
		}

	}

	/**
	 * Read the file
	 * 
	 * @param file , the file to be read
	 * @return void
	 *
	 */
	public void readFile(String file) throws Exception {

		// check that n-gram size and file location has been set

		try {
			// BufferedReader processes files in directory
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = br.readLine()) != null) {

				// remove spaces and other symbols as per instruction in Project forum
				line = line.replaceAll("[^A-Za-z]", "").toLowerCase();

				// make n-grams from the line
				makeNGrams(line);
			}

			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set the n-gram input path
	 * 
	 * @param path of input folder
	 * @return boolean
	 *
	 */
	public boolean setInputPath(String path) {

		if (path == null) {
			return false;
		} else {

			inputDirPath = path;
			return true;
		}

	}

	/**
	 * Set the output file name
	 * 
	 * @param s - the file name
	 * @return boolean
	 *
	 */
	public boolean setOutputName(String s) {

		if (s != null) {
			outputName = s;
			System.out.println("Output file name set to : " + outputName);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Parse each file in the set directory for n-gram processing
	 * 
	 * @param dirPath
	 * @return void
	 *
	 */
	public void parseDirectory() {
		String[] fileList = null;

		try {
			File f = new File(inputDirPath);
			fileList = f.list();
			for (String file : fileList) {
				try {
					readFile(inputDirPath + "/" + file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The folder location cannot be found, double check you specified path.");
		}

	}

	/**
	 * Make n-grams from each line of file
	 * 
	 * @param line - the line read from the file
	 * @return void
	 *
	 */
	private void makeNGrams(String line) {

		// nGram variable to store nGrams
		String nGram = null;
		// the remainder is the portion at end of line missed by loop
		String remainder = null;
		// the fill is the number of underscores added to make the remainder on a line
		// the same length as the n-gram
		int fill = 0;

		
		// convert line (String) to char array to loop through and make n-grams
		char[] arr = line.toCharArray();

		// Array to make substring (n-gram size)
		for (int i = 0; i < arr.length; i += nGramSize) {
			// when i = 0, substring = i + size(2)
			// when i = 2, substring = i + size(4)
			// when i = 4, substring = i + size(6)
			if (arr.length > i + nGramSize) {
				nGram = line.substring(i, nGramSize + i);
				// add full nGram to storage array for processing
				addNGram(nGram);
			} else {
				// deal with remainder of line not parsed
				remainder = line.substring(i, arr.length);
				// check how much needs to be filled to make the n-gram size
				fill = nGramSize - remainder.length();
				if (fill == 1) {
					// add underscore to make remainder same length as n-gram
					addNGram(remainder + "_");
				} else if (fill == 2) {
					// add 2 underscores to make remainder same length as n-gram
					addNGram(remainder + "__");
				} else if (fill == 3) {
					// add 3 underscores to make remainder same length as n-gram
					addNGram(remainder + "___");
				} else if (fill == 4) {
					// add 4 underscores to make remainder same length as n-gram
					addNGram(remainder + "____");
				}

			}
		}

	}
}
