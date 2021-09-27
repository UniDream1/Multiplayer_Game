package Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

import gameStuff_phases.Handler;
import gameobjects_states.GameObject;
import gameobjects_states.ID;

public class Box extends GameObject {

	public Box(int x, int y, ID id, UUID uniqueID, Handler handler) {
		super(x, y, id, uniqueID, handler);

	}

	@Override
	public void tick() {
		x += xVel;
		y += yVel;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
