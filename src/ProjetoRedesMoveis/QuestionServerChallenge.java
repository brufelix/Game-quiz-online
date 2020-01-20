package ProjetoRedesMoveis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class QuestionServerChallenge extends Thread {
	Socket play01;
	DataInputStream in;
	DataOutputStream out;
	Play play;
	InitQuestion initQuestion;
	boolean execute;
	int controlQuestion, points, idPlay, idQuestion = 1;
	String data;
	String datas[];

	public QuestionServerChallenge(Socket play01, int idPlay) throws IOException {
		this.play01 = play01;
		this.play = new Play();
		this.in = new DataInputStream(play01.getInputStream());
		this.out = new DataOutputStream(play01.getOutputStream());
		this.initQuestion = new InitQuestion();
		this.controlQuestion = initQuestion.getArrayListQuestions().size();
		this.idPlay = idPlay;
		this.execute = true;
		this.start();
	}

	public void checkingNameUser() throws Throwable {
		if (!play.isHasUserName()) {
			addNameUser();
		}
	}

	public void setNameUser(String userName) {
		if (this.idPlay == 1) {
			ServerChallenge.nameUser01 = userName;
		} else if (this.idPlay == 2) {
			ServerChallenge.nameUser02 = userName;
		}
	}

	public void addNameUser() throws Throwable {
		data = in.readUTF();
		datas = data.split(" ");
		if (datas[0].equals("nameUser")) {
			setNameUser(datas[1]);
			play.setHasUserName(true);
		}
	}

	public void sendQuestion() throws Throwable {
		try {
			// sleep(3000);
			out.writeUTF(initQuestion.getQuestion(this.idQuestion).getQuestion());
			// sleep(3000);
			out.writeUTF(initQuestion.getQuestion(this.idQuestion).getAlternative01());
			// sleep(500);
			out.writeUTF(initQuestion.getQuestion(this.idQuestion).getAlternative02());
			// sleep(500);
			out.writeUTF(initQuestion.getQuestion(this.idQuestion).getAlternative03());
			// sleep(500);
			out.writeUTF(initQuestion.getQuestion(this.idQuestion).getAlternative04());
			data = in.readUTF();
			checkingAnswer(data);
		} catch (IOException e) {
			play01.close();
			e.printStackTrace();
		}
	}

	public void setReady() {
		if (this.idPlay == 1) {
			ServerChallenge.readyPlay01 = true;
		} else if (this.idPlay == 2) {
			ServerChallenge.readyPlay02 = true;
		}
	}

	public String rightQuestion() {
		return initQuestion.getQuestion(this.idQuestion).getRightQuestion();
	}

	public void addPoints() throws Throwable {
		if (this.idPlay == 1) {
			ServerChallenge.pointsPlay01 += 10;
			controlQuestion();
		} else if (this.idPlay == 2) {
			ServerChallenge.pointsPlay02 += 10;
			controlQuestion();
		}
	}

	public void controlQuestion() throws Throwable {
		if (controlQuestion > 1) {
			controlQuestion--;
			this.idQuestion++;
		} else {
			sleep(2000);
			setReady();
			checkWinner();
			this.execute = false;
			// this.stop();
			play01.close();
		}
	}

	public void sleep(int timeMilins) throws InterruptedException {
		currentThread();
		Thread.sleep(timeMilins);
	}

	public void checkingAnswer(String reply) throws Throwable {
		if (reply.equalsIgnoreCase(rightQuestion())) {
			addPoints();
			// sleep(1000);
			// out.writeUTF("Resposta Certa :)");
			// sleep(1000);
			// out.writeUTF("Proxima Pergunta");
			// sleep(1000);
		} else {
			controlQuestion();
			// sleep(1000);
			// out.writeUTF("Você errou :(");
		}
	}

	public boolean checkWinner() throws Throwable {
		System.out.println();
		while (true) {
			System.out.println();
			if (ServerChallenge.readyPlay01 && ServerChallenge.readyPlay02) {
				System.out.println();
				if (ServerChallenge.pointsPlay01 > ServerChallenge.pointsPlay02) {
					System.out.println();
					out.writeUTF(
							"Ganhador " + ServerChallenge.nameUser01 + " Pontuação: " + ServerChallenge.pointsPlay01);
					out.writeUTF(
							"Pontuação Do joagador " + ServerChallenge.nameUser02 + " " + ServerChallenge.pointsPlay02);
					System.out.println();
					return true;
				} else if (ServerChallenge.pointsPlay01 < ServerChallenge.pointsPlay02) {
					System.out.println();
					out.writeUTF(
							"Ganhador " + ServerChallenge.nameUser02 + " Pontuação: " + ServerChallenge.pointsPlay02);
					out.writeUTF(
							"Pontuação Do jogador " + ServerChallenge.nameUser01 + " " + ServerChallenge.pointsPlay01);
					System.out.println();
					return true;
				} else {
					out.writeUTF("Empate");
					out.writeUTF(
							"Jogador " + ServerChallenge.nameUser01 + " Pontuação: " + ServerChallenge.pointsPlay01);
					out.writeUTF(
							"Jogador " + ServerChallenge.nameUser02 + " Pontuação: " + ServerChallenge.pointsPlay02);
					return true;
				}
			}
		}
	}

	public void run() {
		while (execute) {
			try {
				checkingNameUser();
				// sleep(2000);
				sendQuestion();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}
