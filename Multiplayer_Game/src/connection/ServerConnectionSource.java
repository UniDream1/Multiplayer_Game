package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class ServerConnectionSource {

	private Socket connection;

	private BufferedReader inputChannel;

	private PrintWriter outputChannel;

	@SuppressWarnings("unused")
	private static final String LOG_OFF_NOTIFICATION = "logoff /server:remote_computer_";

	@SuppressWarnings("unused")
	private Encrypter encrypter;

	public ServerConnectionSource(String IP_Address, int port) {

		try {
			connection = new Socket(IP_Address, port);

			System.out.println("connected!");

			inputChannel = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			outputChannel = new PrintWriter(connection.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ServerConnectionSource(String IP_Address, int port, Encrypter encrypter) {

		try {
			connection = new Socket(IP_Address, port);

			inputChannel = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			outputChannel = new PrintWriter(connection.getOutputStream(), true);

			this.encrypter = encrypter;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ServerConnectionSource setUpNewConnection(InetAddress IP_Address, int port) {

		try {
			connection = new Socket(IP_Address, port);

			inputChannel = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			outputChannel = new PrintWriter(connection.getOutputStream(), true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ServerConnectionSource CleanConnection(String Encrypted_LOG_OFF_NOTIFICATION) {
		try {
			if (connection != null) {
				sendMsg(Encrypted_LOG_OFF_NOTIFICATION);
				Thread.sleep(2000);
			}

			connection = null;
			inputChannel = null;
			outputChannel = null;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ServerConnectionSource sendMsg(String encryptedMsg) {
		if (!encryptedMsg.isBlank()) {

			outputChannel.println(encryptedMsg);

			outputChannel.flush();

		}
		return this;
	}

	public boolean isConnected() {
		return connection != null  && connection.isConnected();
	}
	public ServerConnectionSource inititateInputChannelThread() {
		Thread inputChannelThread = new Thread(() -> {
			try {

				while (true) {
					String incoming = inputChannel.readLine();
					if (incoming.contentEquals("request_data")) {

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

	public void initiateConsoleReadingSequence() {
		new Thread(() -> {
			Scanner sc = new Scanner(System.in);
			try {
				for (;;) {
					String msg = sc.nextLine();
					if (msg.equalsIgnoreCase("exit")) {
						return;
					}
					this.sendMsg(msg);
				}
			} finally {
				sc.close();
			}
		}).start();
	}

}
