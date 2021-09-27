package gameStuff_phases;

import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects_states.GameObject;


public class Handler {

	public LinkedList<GameObject> gameObjectList = new LinkedList<>();

	public void tick() {
		gameObjectList.forEach(gameObject -> {
			gameObject.tick();
		});
	}

	public void render(Graphics g) {
		gameObjectList.forEach(gameObject -> {
			gameObject.render(g);
		});
	}

	public boolean isReady() {
		return gameObjectList.size() > 0;
	}

	public void addGameObject(GameObject gameObject) {
		if (!gameObjectList.contains(gameObject)) {
			gameObjectList.add(gameObject);
		}
	}

	public void removeGameObejct(GameObject gameObject) {
		if (gameObjectList.contains(gameObject)) {
			gameObjectList.remove(gameObject);
		}
	}
	

}
