package ProjetoRedesMoveis;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receveid extends Thread {
	Socket client = null;
	DataInputStream in;
	String data;

	public Receveid(Socket aclient) throws Throwable {
		this.client = aclient;
		this.in = new DataInputStream(client.getInputStream());
		this.start();
	}

	public void run() {
		try {
			while (true) {
				data = in.readUTF();
				System.out.println(data);
			}
		} catch (IOException e) {
			try {
				client.close();
			} catch (Exception e2) {
			}
		}
	}
}
