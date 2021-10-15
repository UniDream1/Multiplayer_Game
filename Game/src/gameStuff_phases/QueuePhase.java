package gameStuff_phases;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
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

	public String getDefaultIP() {
		if (this.DEFAULT_IP_ADRESS != null) {
			return this.DEFAULT_IP_ADRESS.toString();
		} else {
			return "";
		}
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

		Long time = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - time > 500l) {
				try {

					if (IP.isBlank() && (port + "").isBlank()) {
						connection = new ServerConnectionSource(this.DEFAULT_IP_ADRESS, defaultPort, game);
						connection.inititateInputChannelThread();
						this.game.setServerConnectionSource(connection);

						break;
					} else {
						if (!this.game.getIPVerifier().setIp(IP).isIpAddress()) {
							
							connection = new ServerConnectionSource(InetAddress.getByName(IP), this.port, game);
							connection.inititateInputChannelThread();
							this.game.setServerConnectionSource(connection);
							break;
						}

					}
				} catch (Exception e) {

				}
				time = System.currentTimeMillis();
			}
		}

		if (connection.isConnected()) {
			game.setServerConnectionSource(connection);
			game.setGameState(GameState.InGame);

		}
	}

}
