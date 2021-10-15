package connection;

import gameStuff_phases.Game;
import gameobjects_states.GameObject;
import gameobjects_states.Type;
import objects.characters.Player;

public class ComsManager {

	private Game game;

	public ComsManager(Game game) {
		this.game = game;
	}

	public void process(String msg) {
		String[] arr = null;
		int id = 0;
		int x = 0;
		int y = 0;
		try {
			arr = msg.split(",");
			id = Integer.parseInt(arr[0].split(":")[1]);
			String pos = arr[1].split(":")[1];
			x = Integer.parseInt(pos.split(";")[0]);
			y = Integer.parseInt(pos.split(";")[1]);
		} catch (NumberFormatException e) {
		}
		Type type = Type.valueOf(arr[2].split(":")[1]);

		boolean found = false;

		for (GameObject obj : game.getHandler().charactersList) {

			if (obj.getUniqueID() == id) {
				obj.setX(x);
				obj.setY(y);
				if (obj.getType() != type) {
					obj.setType(type);
				}
				found = true;
			}
		}
		
		if (!found) {
			game.getHandler().addCharacter(new Player(x, y, type, id));
		}
	}

}
