package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import resources.*;
import matrix.Matrix;


public class Image {
	
	private BufferedImage img;
	private double[][] rgbVals;
	private double[][] originalVals;
	
	//No change in resolution hence const;
	private final int width;
	private final int height;
	private final Matrix matrix;
	
	public BufferedImage getImg() {
		return this.img;
	}

	public Image(String PATH) {
	      BufferedImage img = null;
	      File f = null;
	      
	      try {
	    	  f = new File(PATH);
	          img = ImageIO.read(f);
	      } catch (IOException e) {
	            System.out.println(e);
	      }
	      
	      System.out.println("Image loaded");
	      this.img = img;
	      width = this.img.getWidth();
	      height = this.img.getHeight();
	      this.convertToRGB();

	      this.originalVals = this.rgbVals; //Test
	      this.matrix = new Matrix(rgbVals);
	      System.out.println("Converted to Matrix");
	}
	
    private void convertToRGB() {
        double[][] RGB_Values = new double[this.height][];
        for(int y=0; y<this.height; y++){
            double[] pixelRow = new double[this.width];
            for(int x=0; x<this.width; x++){
                Integer RGB = this.img.getRGB(x, y);        
                pixelRow[x] = RGB.doubleValue();
                
                if(y<10) {
                	 int blue = RGB & 0xff;
                     int green = (RGB & 0xff00) >> 8;
                     int red = (RGB & 0xff0000) >> 16;

                     System.out.print("[" + red + " " + blue + " " + green + "] ");
                }
            }
            System.out.println();
            RGB_Values[y] = pixelRow;
        }
        this.rgbVals = RGB_Values;
    }

    public double[] returnRGBAtPos(int width, int height){
        Integer RGB = (int) this.rgbVals[height][width];
        int blue = RGB & 0xff;
        int green = (RGB & 0xff00) >> 8;
        int red = (RGB & 0xff0000) >> 16;
        return new double[]{red,blue,green};
    }
    
    public int setImageOffset(Integer offset){
    	try {
    		double[][] RGB_Values = new double[this.height][];
	        for(int y=0; y<this.height; y++){
	        	double[] pixelRow = new double[this.width];
	            for(int x=0; x<this.width; x++){
	                Integer RGB = (int) this.rgbVals[y][x];
	                int blue = RGB & 0xff;
	                int green = (RGB & 0xff00) >> 8;
	                int red = (RGB & 0xff0000) >> 16;
	            
	                Integer newRGB = new Color(red+offset, blue+offset, green+offset).getRGB();
	                pixelRow[x] = (newRGB);
	            }
	            RGB_Values[y] = (pixelRow);
	        }
            this.rgbVals = RGB_Values;
    	} catch(Exception err) {
    		System.out.println(err);
    		return Constants.FAILURE;
    	}
        return Constants.SUCCESS;
    }
    
    public void setImageMatrix(double[][] rgbValues) {
        this.rgbVals =  rgbValues.clone();
    }
    
    public int compressImage(int k) {
    	try {
    		System.out.println("In Compress");
    		this.matrix.reConstruct(k);
    		this.rgbVals = (this.matrix.getAP());
    	} catch(Exception e) {
    		System.out.println(e);
    		return Constants.FAILURE;
    	}
    	return Constants.SUCCESS;
    }
    public int updateImage(String Path) {	
        
        for(int y=0; y<this.height; y++){
            for(int x=0; x<this.width; x++){
            	img.setRGB(x, y, (int)this.originalVals[y][x]);
            }
        }
        try {
            File f = new File(Path);
            if(ImageIO.write(img, "jpg", f) == false) {
            	return Constants.FAILURE;
            }
        } catch (IOException e) {
            System.out.println(e);
            return Constants.FAILURE;
        }
        return Constants.SUCCESS;
    }
    
    public void printImageRGB(int dimension){
        // Prints first dimensionxdimension square matrix rgb values 
        for(int y=0; y<dimension; y++){
            for(int x=0; x<dimension; x++){

                /* Combined RGB VALUE */
                Integer RGB = (int) this.rgbVals[y][x];

                /* Evaluate red/blue/green values */
                int blue = RGB & 0xff;
                int green = (RGB & 0xff00) >> 8;
                int red = (RGB & 0xff0000) >> 16;

                System.out.print("[" + red + " " + blue + " " + green + "] ");
            }
            System.out.println();
        }
    }
	
}
