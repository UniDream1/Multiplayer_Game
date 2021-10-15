package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import gameStuff_phases.Game;

public class ServerConnectionSource {

	private Socket connection;

	private BufferedReader inputChannel;

	private PrintWriter outputChannel;

	private ComsManager comsManager;

	@SuppressWarnings("unused")
	private static final String LOG_OFF_NOTIFICATION = "logoff /server:remote_computer_";

	public ServerConnectionSource(String IP_Address, int port, Game game) {

		try {
			connection = new Socket(IP_Address, port);

			System.out.println("connected!");

			inputChannel = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			outputChannel = new PrintWriter(connection.getOutputStream());

			comsManager = new ComsManager(game);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ServerConnectionSource(InetAddress IP, int port, Game game) {

		try {
			connection = new Socket(IP, port);

			System.out.println("connected!");

			inputChannel = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			outputChannel = new PrintWriter(connection.getOutputStream());

			comsManager = new ComsManager(game);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendType(String type) {
		if (this.outputChannel != null) {
			sendMsg("type:" + type);
		}
	}

	public void sendMsg(String encryptedMsg) {
		if (!encryptedMsg.isBlank()) {
			outputChannel.println(encryptedMsg);
			outputChannel.flush();
		}
	}

	public ServerConnectionSource inititateInputChannelThread() {
		Thread inputChannelThread = new Thread(() -> {
			try {

				while (true) {
					String incoming = inputChannel.readLine();
					if (incoming.startsWith("request_data")) {
						// push everything

					} else {
						comsManager.process(incoming);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}

		});
		inputChannelThread.start();
		return this;
	}

	public boolean isConnected() {
		return connection != null && connection.isConnected();
	}

}
