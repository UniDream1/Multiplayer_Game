package objects.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animation.Animation;
import gameobjects_states.GameObject;
import gameobjects_states.Type;

@SuppressWarnings("unused")
public class Player extends GameObject {

	private Animation playerAnimation;

	private BufferedImage plant;
	private BufferedImage fire;
	private BufferedImage water;

	public Player(int x, int y, Type type, int id) {
		super(x, y, type, id);

		this.playerAnimation = new Animation(4, plant);

		this.setxVel(1);
		this.setyVel(1);

		
		
		
	}

	@Override
	public void tick() {
		playerAnimation.animate();
	}

	@Override
	public void render(Graphics g) {
		if (getPreviousCoordinates().x != this.x) {
			this.playerAnimation.drawAnimation(g, x, y);
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
