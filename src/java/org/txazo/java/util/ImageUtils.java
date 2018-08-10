package org.txazo.java.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 */
public class ImageUtils {

	public static final int ZOOM_IN = 1;
	public static final int ZOOM_OUT = 2;

	private static BufferedImage flexImage(BufferedImage srcImage, int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, srcImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(srcImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 图片等比伸缩
	 * 
	 * @param srcImage 原始图片
	 * @param newImage 伸缩图片
	 * @param times 伸缩倍数
	 * @param type 伸缩类型(ZOOM_IN放大, ZOOM_OUT缩小)
	 */
	public static void zoomRatioImage(String srcImage, String newImage, int times, int type) {
		BufferedImage image = null;
		File file = new File(srcImage);
		if (file.exists()) {
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (image != null) {
			int width = image.getWidth();
			int height = image.getHeight();
			if (type == ZOOM_IN) {
				width = width * times;
				height = height * times;
			} else {
				width = width / times;
				height = height / times;
			}
			image = flexImage(image, width, height);
			try {
				ImageIO.write(image, "JPG", new File(newImage));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void zoomImage(String srcImage, String newImage, int width, int height) {
		BufferedImage image = null;
		File file = new File(srcImage);
		if (file.exists()) {
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (image != null) {
			image = flexImage(image, width, height);
			try {
				ImageIO.write(image, "gif", new File(newImage));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		zoomImage("D:/MyPhoto/xly/x1.jpg", "D:/MyPhoto/xly/x1_1.gif", 200, 100);
	}

}
