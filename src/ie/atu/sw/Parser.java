package ie.atu.sw;

import java.io.BufferedReader;
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

	public Parser() {
		// TODO: Set this using the n-gram size (n) i.e. 26^n is the max amount of rows
		// needed for an n-gram
		freqTable = new Object[11100][2];
	}
	
	/**
	 * Execute the n-gram builder
	 * 
	 * @param void
	 * @return void TODO: Should return something for feedback????
	 *
	 */
	public void executeNGramBuilder() {
		// check that size, output path, and input directory path are all specified
		if((inputDirPath !=null) && (nGramSize != 0)) {
			// process the directory
			parseDirectory();
		} else {
			System.out.println("ERROR: Specify N-Gram Size & Input/Output Directory Paths");
		}
		
		
	}

	/**
	 * Add the n-gram to the frequency table
	 * 
	 * @param nGram - the n-gram
	 * @return vid
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
	 * Process the files
	 * 
	 * @param location , the folder where the files are
	 * @return boolean
	 *
	 */
	public void processNGrams(String file) throws Exception {

		// check that n-gram size and file location has been set

		try {
			//FileWriter fw = new FileWriter("out.txt");
			
			// BufferedReader processes files in directory 
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = br.readLine()) != null) {

				// do some business here n-gramming
				line = line.replaceAll("[^A-Za-z]", "");
				makeNGrams(line);
				// fw.write(line + " by Alex Turner zzz" + "\n");
			}

			br.close();
//			fw.flush();
//			fw.close();

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
	 * Parse each file in the set directory for n-gram processing
	 * 
	 * @param dirPath
	 * @return boolean
	 *
	 */
	public void parseDirectory() {
		
		if(inputDirPath != null) {
			File f = new File(inputDirPath);
			String[] fileList = f.list();
			

			for (String file : fileList) {
				System.out.println(file);
				// process each file 
				try {
					processNGrams(inputDirPath + "/" + file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("No Input Directory Specified!");
		}

		

	}

	private void makeNGrams(String s) {
				
				System.out.println(s);
				
				String x = null;
				
				char[] arr = s.toCharArray();
				
				
				// Array to make substring (n-gram size)
				for(int i=0; i<arr.length ; i+=nGramSize) {
					// when i = 0, substring = i + size(2)
					// when i = 2, substring = i + size(4)
					// when i = 4, substring = i + size(6)
					
					if(arr.length > i+nGramSize) {
						x = s.substring(i, nGramSize + i);
						System.out.println(x);
					} else {
						// how do i handle last piece of the line & the next line?
						
					}
				}
								
			}
	}
