package fms.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private static final int PORT = 1234;
	private InetAddress host;

	public Client() throws UnknownHostException {
		host = InetAddress.getLocalHost();
		System.out.println("Connected to host: " + host);
		System.out.println("Type 'help' for Options");

	}

	private void run() throws IOException {
		Socket link = new Socket(host, PORT);
		BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
		PrintWriter out = new PrintWriter(link.getOutputStream(), true);
		Scanner consoleInput = new Scanner(System.in);
		String message, response = null;
		new Thread(new CheckServer(in)).start();

		do {

			message = consoleInput.nextLine();
			out.println(message);

		} while (!message.equals("close"));
		link.close();
	}

	public static void main(String[] args) throws Exception {
		try {
			Client echoClient;
			echoClient = new Client();
			echoClient.run();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
