package com.rave.visiit.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHelper {
	
	public static byte[] scaleImage(byte[] byteArray, int x1, int y1, int w,
			int h) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
		Integer x2 = x1 + w;
		Integer y2 = y1 + h;
		BufferedImage bi = ImageIO.read(in);
		bi = cropImage(bi, x1, y1, x2, y2);
		ImageIO.write(bi, "jpeg", out);
		return out.toByteArray();
	}
	
	public static BufferedImage cropImage(BufferedImage bi, int x1, int y1,
			int x2, int y2) {
		int newWidth = x2 - x1;
		int newHeight = y2 - y1;

		BufferedImage result = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_BGR);
		Graphics2D g = result.createGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, newWidth, newHeight);
		g.drawImage(bi, 0, 0, newWidth, newHeight, x1, y1, x2, y2, null);
		g.dispose();
		return result;
	}
	
}
