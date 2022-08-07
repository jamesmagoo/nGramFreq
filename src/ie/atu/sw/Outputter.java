/**
 * 
 */
package ie.atu.sw;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class deals with outputting the processed n-gram information
 * 
 * @author James McGauran
 * @date 7 Aug 2022
 *
 */
public class Outputter {
	
	private String outputName;
	
	public void setOutputName(String s) {
		
		if(s != null) {
			outputName  = s;
			System.out.println("Output file name set to : " + outputName);
		}
	}
	
	public void outputResults(String line) {
		
		try {
			FileWriter fw = new FileWriter(outputName+".csv");
			fw.write(line + " by Alex Turner zzz" + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
