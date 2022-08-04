package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class nGramBuilder {

	private int size;
	private String location;

	/**
	 * Set the file directory
	 * 
	 * @param null
	 * @return boolean
	 *
	 */
	public boolean setDirectory() {
		JFileChooser chooser = new JFileChooser(new java.io.File("."));
		chooser.setDialogTitle("Select Folder For N-Gram Builder");
		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//		      System.out.println("getCurrentDirectory(): " 
//		         +  chooser.getCurrentDirectory());
//		      location = chooser.getCurrentDirectory();
			location = chooser.getSelectedFile().getPath();

			try {
				FileWriter fw = new FileWriter("out.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(location))));
				String line = null;
				
				while((line = br.readLine())!= null) {
					System.out.println(line);
					fw.write(line + " by Alex Turner" + "\n");
				}
				
				br.close();
				fw.flush();
				fw.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		} else {
			System.out.println("No Selection ");
			return false;
			
			// more testing of github
		}

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
			return true;
		}

	}
}
