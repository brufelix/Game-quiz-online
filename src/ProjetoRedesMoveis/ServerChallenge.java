package ProjetoRedesMoveis;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChallenge {
	static ArrayList<Socket> plays = new ArrayList<Socket>();
	static String nameUser01 = "";
	static String nameUser02 = "";
	static int pointsPlay01 = 0;
	static int pointsPlay02 = 0;
	static boolean readyPlay01 = false;
	static boolean readyPlay02 = false;

	public static void main(String[] args) throws Throwable {
		int serverPort = Integer.parseInt(args[0]);
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(serverPort);
		Socket client;
		int idPlay = 1;
		try {
			while (true) {
				client = server.accept();
				plays.add(client);
				if (plays.size() == 2) {
					@SuppressWarnings("unused")
					QuestionServerChallenge serverQuestion01 = new QuestionServerChallenge(plays.get(0), idPlay);
					idPlay++;
					@SuppressWarnings({ "unused" })
					QuestionServerChallenge serverQuestion02 = new QuestionServerChallenge(plays.get(1), idPlay);
					idPlay = 0;
					plays.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
