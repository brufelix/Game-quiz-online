package ProjetoRedesMoveis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Send extends Thread {
	private static Scanner input = new Scanner(System.in);
	Socket client = null;
	DataInputStream in;
	DataOutputStream out;
	String data, nameUser = "nameUser";

	public Send(Socket aclient) throws Throwable {
		this.client = aclient;
		out = new DataOutputStream(client.getOutputStream());
		in = new DataInputStream(client.getInputStream());
		this.start();
	}

	public void checkingNameUser(String name) throws IOException {
		if (nameUser.equals("nameUser")) {
			System.out.println("Insira o nome de Usuário: ");
			data = input.nextLine();
			out.writeUTF("nameUser " + data);
			nameUser = "ok";
		}
	}

	@SuppressWarnings("deprecation")
	public void run() {
		while (true) {
			try {
				checkingNameUser(this.nameUser);
				data = input.nextLine();
				out.writeUTF(data);
			} catch (IOException e) {
				try {
					this.destroy();
					client.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
