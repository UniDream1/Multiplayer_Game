package window;

import java.awt.Dimension;

import javax.swing.JFrame;

import gameStuff_phases.Game;

public class Window {
	
	ScreenModifier fullScreen = new ScreenModifier();

	public Window(int width, int height, Game game) {

		JFrame frame = new JFrame();

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		frame.add(game);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		fullScreen.setToFullScreen(fullScreen.getAvailableDisplayMode(), frame);
		
		frame.setVisible(true);
		frame.pack();
		
		
	}

}
