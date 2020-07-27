package matrix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import resources.Constants;

public class ColorMatrixDriver {
	private BufferedImage img;
	private int width, height;
	private int[][] rgbVals;
	private ColorMatrix matrix;
	public ColorMatrixDriver(String PATH) {
		BufferedImage img = null;
		File f = null;

		try {
			f = new File(PATH);
			img = ImageIO.read(f);
			System.out.println("Found the image");
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Image loaded");
		this.img = img;
		width = this.img.getWidth();
		height = this.img.getHeight();
		this.getRGBMatrix();
		this.matrix = new ColorMatrix(this.rgbVals);
	}
	private void getRGBMatrix() {
		int[][] rgb_Values = new int[width][height];
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j ++) {
				rgb_Values[i][j] = img.getRGB(i, j);
			}
		}
		this.rgbVals = rgb_Values;
	}
	public int CompressImage(int k) {
		try {
			System.out.println("Inside colorMatrix.compressor");
			this.rgbVals = this.matrix.compress(k);
			System.out.println("Done Compressing");
		} catch(Exception e) {
			System.out.println(e);
			return Constants.FAILURE;
		}
		return Constants.SUCCESS;
	}

	public int updateImage(String Path) {	
		System.out.println("Updating the Image...");
		for(int i = 0; i < this.width; i ++) {
			for(int j = 0; j < this.height; j ++) {
				img.setRGB(i, j, this.rgbVals[i][j]);
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


}
