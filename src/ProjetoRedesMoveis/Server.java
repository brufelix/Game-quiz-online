package ProjetoRedesMoveis;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		Socket client = null;
		int id = 1;
		ArrayListPlay plays = new ArrayListPlay();
		OrgClassification classification = new OrgClassification();
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
			while (true) {
				client = server.accept();
				Play play = new Play();
				plays.add(play);
				play.setId(id++);
				@SuppressWarnings("unused")
				QuestionServer questionServer = new QuestionServer(client, plays, play.getId(), classification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
