package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResizeImage {
	private double lw;
	private double lh;
	public ResizeImage() {
		double lw = 1024.0;
		double lh = 768.0;
	}

	// public void execute(File fromFile) throws IOException {
	public File execute(File fromFile) throws IOException {

		BufferedImage imageBefore = null;
		try {
			imageBefore = ImageIO.read(new File(fromFile.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] beforeData = getBytesFromImage(imageBefore, "jpg");

		double lw = 1024.0;
		double lh = 768.0;

		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(beforeData));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		double w = 0;
		double h = 0;
		if (bufferedImage.getWidth() > bufferedImage.getHeight()) {
			h = lh;
			w = bufferedImage.getWidth() * (lh / bufferedImage.getHeight());
			if (lw > w) {
				h = h * (lw / w);
				w = lw;
			}
		} else {
			w = lw;
			h = bufferedImage.getHeight() * (lw / bufferedImage.getWidth());
			if (lh > h) {
				w = w * (lh / h);
				h = lh;
			}
		}
		ImageIO.write(resize(bufferedImage, (int) lw, (int) lh, (int) w, (int) h), "jpg", baos);
		baos.flush();
		byte[] afterData = baos.toByteArray();

		BufferedImage imageAfter = null;
		imageAfter = getImageFromBytes(afterData);
		String str = fromFile.toString();

		String reg = ".jpg|.JPG|.jpeg";

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		String strAfter = matcher.replaceAll("_aft.jpg");

		ImageIO.write(imageAfter, "jpeg", new File(strAfter));
		baos.close();
		return new File(strAfter);
	}

	private static BufferedImage resize(BufferedImage bufferedImage, int layoutWidth, int layoutHeight, int imageWidth,
			int imageHeight) {

		BufferedImage scaledBI = new BufferedImage(layoutWidth, layoutHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = scaledBI.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, layoutWidth, layoutHeight);
		int x = (layoutWidth - imageWidth) / 2;
		int y = (layoutHeight - imageHeight) / 2;
		g.drawImage(bufferedImage, x, y, imageWidth, imageHeight, null);
		g.dispose();
		return scaledBI;
	}

	//
	public static byte[] getBytesFromImage(BufferedImage img, String format) throws IOException {
		if (format == null) {
			format = "png";
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, format, baos);
		return baos.toByteArray();
	}

	//
	public static BufferedImage getImageFromBytes(byte[] bytes) throws IOException {
		ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
		BufferedImage img = ImageIO.read(baos);
		return img;
	}
}
