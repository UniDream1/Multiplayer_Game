package imageloader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	public BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageIO.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

	}

}
