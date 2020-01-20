package ProjetoRedesMoveis;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PlayUser {
	static Socket client = null;
	public static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws UnknownHostException, Throwable {
		int port, foi = 0;
		String ip;
		try {
			while (foi != 1) {
				System.out.println("DIGITE O MODO DE JOGO");
				System.out.println("1 - Para o Modo Ranking: port 33000");
				System.out.println("2 - Para o Modo Desafio: port 44000");
				String modo = entrada.nextLine();

				if (modo.equals("1")) {
					System.out.println("Insira o IP do servidor: ");
					ip = entrada.nextLine();
					System.out.println("Insira o porta do servidor: ");
					port = entrada.nextInt();
					Socket client = new Socket(ip, port);
					@SuppressWarnings("unused")
					Send send = new Send(client);
					@SuppressWarnings("unused")
					Receveid receveid = new Receveid(client);
					foi = 1;
				} else if (modo.equals("2")) {
					System.out.println("Insira o IP do servidor: ");
					ip = entrada.nextLine();
					System.out.println("Insira o porta do servidor: ");
					port = entrada.nextInt();
					Socket client = new Socket(ip, port);
					@SuppressWarnings("unused")
					Send send = new Send(client);
					@SuppressWarnings("unused")
					Receveid receveid = new Receveid(client);
					foi = 1;
				} else {
					System.out.println("Modo não existe!\n");
				}
			}

		} catch (Exception e) {
			client.close();
		}
	}
}