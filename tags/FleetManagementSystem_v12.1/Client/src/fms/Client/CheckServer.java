package fms.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class CheckServer implements Runnable {
	private BufferedReader in;

	public CheckServer(BufferedReader in2) {
		in = in2;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
