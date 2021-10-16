package gameStuff_phases;

import java.awt.Font;
import java.awt.Graphics;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import connection.ServerConnectionSource;
import gameobjects_states.GameState;

public class QueuePhase {

	private Game game;

	private ServerConnectionSource connection;

	private int defaultPort = 32768;
	private InetAddress DEFAULT_IP_ADRESS;

	private int port;
	private String IP = "";
	private String dots = "";
	private Long Time = System.currentTimeMillis();

	//@formatter:off
	public QueuePhase(Game game) {
		try {
			this.DEFAULT_IP_ADRESS = Inet4Address.getByName("www.aegame.stevetec.de");
		} catch (UnknownHostException e) {}
		this.game = game;
	}
	//@formatter:on

	public void setIPAddress(String IP) {
		this.IP = IP;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void Render(Graphics g) {

		g.setFont(new Font("Arial", Font.PLAIN, 40));
		int width = g.getFontMetrics().stringWidth("connecting to server");
		g.drawString("connecting to server" + dots, (game.getWidth() - width) / 2,
				game.getHeight() / 2 - g.getFontMetrics().getHeight() / 4);

		if (System.currentTimeMillis() - Time > 333 * 4) {
			dots = "...";
			Time = System.currentTimeMillis();
		} else if (System.currentTimeMillis() - Time > 333 * 2) {
			dots = "..";

		} else if (System.currentTimeMillis() - Time > 333) {
			dots = ".";
		}

	}

	public void startQueuing() {

		setIPAddress(this.game.getSettingsMenu().getIPAddress());

		try {

			if (!this.game.getIPVerifier().setIp(IP).isIpAddress()) {

				connection = new ServerConnectionSource(DEFAULT_IP_ADRESS, this.port, game);

				if (connection != null && connection.isConnected()) {
					connection.inititateInputChannelThread();
					game.setServerConnectionSource(connection);
					game.setGameState(GameState.InGame);

				}

			} else {
				connection = new ServerConnectionSource(this.DEFAULT_IP_ADRESS, defaultPort, game);
				connection.inititateInputChannelThread();
				this.game.setServerConnectionSource(connection);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
