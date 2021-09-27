package inputlisteners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameStuff_phases.Game;
import gameobjects_states.GameState;
import gameobjects_states.ID;

public class KeyInputs extends KeyAdapter {

	@SuppressWarnings("unused")
	private Game game;

	private ArrayList<Integer> keys;

	public KeyInputs(Game game) {
		this.game = game;

		keys = new ArrayList<>();
	}

	private void handlePlayerKeyPressedEvent(KeyEvent e) {
		if (game.getGameState().equals(GameState.InGame)) {
			game.getHandler().gameObjectList.forEach(i -> {
				if (i.getID().equals(ID.Player)) {

				//check for keyKombination which is pressed
					if (keys.contains(KeyEvent.VK_W)) {
						// W pressed move forward
					}

					// player movement

				}
			});
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (!keys.contains(e.getKeyCode())) {
			keys.add(e.getKeyCode());
		}
		handlePlayerKeyPressedEvent(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		if (keys.contains(e.getKeyCode())) {
			keys.remove(e.getKeyCode());
		}

	}
}
