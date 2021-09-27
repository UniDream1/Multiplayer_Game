package gameobjects_states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

import gameStuff_phases.Handler;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int xVel;
	protected int yVel;
	protected ID id;
	protected UUID uniqueID;

	public GameObject(int x, int y, ID id, UUID uniqueID, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.uniqueID = uniqueID;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	
	
	public ID getId() {
		return id;
	}

	public UUID getUniqueID() {
		return uniqueID;
	}

	public ID getID() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getxVel() {
		return xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

}
