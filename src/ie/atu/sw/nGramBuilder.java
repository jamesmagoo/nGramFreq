package ie.atu.sw;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class nGramBuilder {

	private int size;
	private String location;

	
	/**
	 * Set the n-gram input path
	 * 
	 * @param path of input folder
	 * @return boolean
	 *
	 */
	public boolean setInputPath(String path) {
		location = path;
		System.out.println(location);
		return true;
	}
	
	/**
	 * Set the n-gram size for processing
	 * 
	 * @param n - the n-gram size (max 5)
	 * @return boolean
	 *
	 */
	public boolean setNGramSize(int n) {

		if (n == 0 || n > 5) {
			return false;
		} else {
			size = n;
			System.out.println("N-Gram Size Set to :" + size);
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
	public boolean processNGrams(String location) {
		
		System.out.println("Folder path set: " + location);
			
		// check that n-gram size and file location has been set
		try {
			FileWriter fw = new FileWriter("out.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./tranquility.txt"))));
			
			
			String line = null;
			
			while((line = br.readLine())!= null) {
				// do some business here n-gramming
				line = line.replaceAll("[^A-Za-z]", "");
				makeNGrams(line);
				//fw.write(line + " by Alex Turner zzz" + "\n");
			}
			
			br.close();
			fw.flush();
			fw.close();
			return true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Proccessing");
		
		return true;
		
	}
	
	private void makeNGrams(String s) {
		List<String> ngrams = new ArrayList<String>();
		
		System.out.println(s);
		String x = s.substring(0, 2);
		System.out.println(x);
		
		char[] arr = s.toCharArray();
		
		for(int i=0; i<arr.length ; i++) {
			System.out.print(arr[i] + ",");
		}
	}
}
