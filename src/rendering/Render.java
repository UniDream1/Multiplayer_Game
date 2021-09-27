package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gameStuff_phases.Game;
import gameStuff_phases.Handler;
import gameStuff_phases.Menu;
import gameStuff_phases.QueuePhase;
import gameobjects_states.GameState;
import imageloader.BufferedImageLoader;

public class Render {

	private Game game;

	private Handler handler;

	@SuppressWarnings("unused")
	private BufferedImageLoader imageloader;

	@SuppressWarnings("unused")
	private BufferedImage sprite_Sheet;

	private Menu menu;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	QueuePhase queue;

	public Render(Game game, Handler handler) {
		this.game = game;

		this.handler = handler;

		this.imageloader = game.getImageLoader();

		this.menu = game.getMenu();

		this.queue = game.getQueue();
		// sprite_Sheet = this.imageloader.loadImage(" image URL ");
	}

	public void commenceRenderingSequence() {
		BufferStrategy buffer = game.getBufferStrategy();
		if (buffer == null) {
			game.createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();

		//@formatter:off 					rendering 										//
		
		g.setColor(Color.black);//background color
		g.fillRect(0, 0, screenSize.width, screenSize.height);//rectangle as a background 			// load background image for menu right here
	
		
		//load menu
		if (game.getGameState().equals(GameState.Menu)) {
			menu.render(g); 		
		}else if(game.getGameState().equals(GameState.InQueue)) {
			//renders queue-phase and establishes connection to the server
			g.setColor(Color.DARK_GRAY);
			queue.Render(g);
			
			
		}
		
		
		if (handler.isReady()) { // checks, if handler contains any Objects to render
		//	handler.render(g);
		}
		
		//								 end of rending                          				//

		//@formatter:on

		g.dispose();
		buffer.show();
	}

}
