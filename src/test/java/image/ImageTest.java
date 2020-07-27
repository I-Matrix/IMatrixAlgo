package image;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import image.Image;
import matrix.ColorMatrixDriver;
import matrix.Matrix;
import resources.Constants;
public class ImageTest {

	@Test
	public void testImage() {
//		/* Variable Declarations */
//		
//		/** SET PATHS! **/ 
//	    String home = System.getProperty("user.home");
//	    String fileNameRead = "SmartDog";
//	    String fileNameWrite = "updated";
//	    
//		Image image = new Image(home+"/Downloads/" + fileNameRead + ".jpg");
//		
//		System.out.println("About to compress image");
//		if(image.compressImage(50) == Constants.SUCCESS) {
//			System.out.println("Sucessfully Compressed!");
//			if(image.updateImage(home+"/Downloads/" + fileNameWrite + ".jpg") == Constants.SUCCESS) {
//				System.out.println("Successfully Updated!");
//			} else {
//				System.out.println("Unsucessfull in Update");
//			}
//		}
		
	    
	}
	
	@Test
	public void testMatrix() {
//		Double[][] temp = new Double[3][];
//		Double[] temp_arr1 = {1.,2.,3.};
//		Double[] temp_arr2 = {1.,3.,2.};
//		Double[] temp_arr3 = {2.,3.,1.};
//		temp[0] = temp_arr1;
//		temp[1] = temp_arr2;
//		temp[2] = temp_arr3;
//		
//		Matrix tempMatrix = new Matrix(temp);
//		tempMatrix.reConstruct(3);
		
		System.out.println("Inside Test Matrix");
	 	String home = System.getProperty("user.home");
	    String fileNameRead = "minion";
	    String fileNameWrite = "updated";
	    
		//Image image = new Image(home+"/Downloads/" + fileNameRead + ".jpg");
	    int k = 100;
		ColorMatrixDriver testing = new ColorMatrixDriver(home+"/Downloads/" + fileNameRead + ".jpg");
		testing.CompressImage(k);
		testing.updateImage(home+"/Downloads/" + fileNameWrite + ".jpg");
//		image.printImageRGB(10);
//		for(int y=0; y<10; y++) {
//			for(int x=0; x<10; x++) {
//				double[] rgb_values = image.returnRGBAtPos(x, y);
//				System.out.print("["+rgb_values[0]+","+rgb_values[1]+","+rgb_values[0]+"]");
//			}
//			System.out.println();
//		}
			
	}

}
