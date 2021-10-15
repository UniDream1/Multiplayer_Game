package objects.characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameobjects_states.GameObject;
import gameobjects_states.Type;

public class Projectile extends GameObject {

	public Projectile(int x, int y, Type type, int id) {
		super(x, y, type, id);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
