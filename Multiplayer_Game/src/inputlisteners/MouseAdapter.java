package inputlisteners;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import gameStuff_phases.Game;

public class MouseAdapter implements MouseInputListener {

	private Game game;

	public MouseAdapter(Game game) {
		this.game = game;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.getMenu().onMouseMovedEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.getMenu().onMousepressedEvent(e);
	}

	//@formatter:off
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

}
