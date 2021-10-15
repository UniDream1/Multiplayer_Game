package inputlisteners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameStuff_phases.Game;

public class KeyInputs extends KeyAdapter {

	private Game game;

	public KeyInputs(Game game) {
		this.game = game;

	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		super.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		switch (this.game.getGameState()) {
		case Menu:
			this.game.getMenu().onkeyPressedEvent(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

	}
}
