package rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.UUID;

import gameStuff_phases.Game;
import gameobjects_states.Type;
import objects.characters.Floor;
import objects.characters.Wall;

@SuppressWarnings("unused")
public class GameScene {

	private Game game;
	//@formatter:off
	private final int []gridSize = {
		60,60,	//wall xSize,ySize	
		60,60,	// floor 
		0,0		// some other element
	};
	//@formatter:on

	private final double ORIGINAL_WIDTH = 1920.0;
	private final double ORIGINAL_HEIGHT = 1080.0;

	private int xSize;
	private int ySize;
	private int scaledSizeX;
	private int scaledSizeY;

	private double xScale;
	private double yScale;

	private BufferedImage img;

	public GameScene(Game game) {
		this.game = game;

		this.xScale = (double) this.game.getWidth() / this.ORIGINAL_WIDTH;
		this.yScale = (double) this.game.getHeight() / this.ORIGINAL_HEIGHT;

		this.xSize = (int) (this.xScale * gridSize[0]);
		this.ySize = (int) (this.yScale * gridSize[1]);

		this.img = game.getImageLoader().loadImage("/RedBlueCollisonmap.png"); // background ======> path is provided

		scaledSizeX = (int) (this.xScale * this.ORIGINAL_WIDTH);
		scaledSizeY = (int) (this.yScale * this.ORIGINAL_HEIGHT);

		/*
		 * BufferedImage bi = new BufferedImage(this.img.getWidth(),
		 * this.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		 * 
		 * Graphics g = bi.createGraphics();
		 * 
		 * g.setColor(Color.black); g.fillRect(0, 0, this.img.getWidth(),
		 * this.img.getHeight());
		 * 
		 * for (int i = 0; i < this.img.getWidth(); i += this.xSize) { for (int j = 0; j
		 * < this.img.getHeight(); j += this.ySize) {
		 * 
		 * int pixel = this.img.getRGB(i, j);
		 * 
		 * Color rgb = new Color(pixel);
		 * 
		 * if (rgb.getRed() == 255) { g.setColor(Color.red); g.fillRect(i, j,
		 * this.xSize, this.ySize); continue; } else if (rgb.getBlue() == 255) {
		 * g.setColor(Color.blue); g.fillRect(i, j, this.xSize, this.ySize); continue; }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * g.dispose();
		 * 
		 * try { ImageIO.write(bi, "png", new File("Images/copyyy.png")); } catch
		 * (IOException e) {
		 * 
		 * }
		 */
		for (int i = 0; i < this.img.getWidth(); i += gridSize[0]) {
			for (int j = 0; j < this.img.getHeight(); j += gridSize[1]) {

				int argb = this.img.getRGB(i, j);

				Color pixel = new Color(argb);

				int xPos = (int) ((double) i * this.xScale);
				int yPos = (int) ((double) j * this.yScale);

				if (pixel.getRed() == 255) {

					Floor floor = new Floor(xPos, yPos, Type.none, UUID.randomUUID().version(), this.game);
					floor.setSize(xSize, ySize);

					this.game.getHandler().addGameObject(floor);

					continue;

				} else if (pixel.getBlue() == 255) {

					Wall wall = new Wall(xPos, yPos, Type.none, UUID.randomUUID().version(), this.game);
					wall.setSize(xSize, ySize);

					this.game.getHandler().addGameObject(wall);

					continue;
				}
			}
		}
	}

}
