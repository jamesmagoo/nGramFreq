/**
 * 
 */
package ie.atu.sw;

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
	

}
