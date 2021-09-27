package gameStuff_phases;

import java.awt.Font;
import java.awt.Graphics;

import connection.ServerConnectionSource;

public class QueuePhase {

	private Game game;

	private ServerConnectionSource connection;

	private int port = 42736;
	private String IP = "localhost";

	private boolean isInQueuePhase = true;

	public QueuePhase(Game game) {
		this.game = game;

	}

	public void Render(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("connecting to server...", game.getWidth() / 2 - 200, game.getHeight() / 2 - 30);

	}

	public boolean isCompleted() {
		return isInQueuePhase;
	}

	public void startQueuing() {
		try {
			connection = new ServerConnectionSource(IP, port);
			connection.inititateInputChannelThread().initiateConsoleReadingSequence();
			if (connection.isConnected()) {
				isInQueuePhase = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
