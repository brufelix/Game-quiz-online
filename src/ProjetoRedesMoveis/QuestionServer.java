package ProjetoRedesMoveis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class QuestionServer extends Thread {
	ArrayListPlay ArrayListplays;
	OrgClassification classification;
	Socket client;
	InitQuestion initQuestion;
	DataInputStream in;
	DataOutputStream out;
	Play play;
	int id, controlQuestion, idQuestion = 1;
	boolean hasUserName = false;
	String[] datas;
	String data;
	Play[] plays = new Play[10];

	public QuestionServer(Socket aclient, ArrayListPlay ArraListplays, int id, OrgClassification OC)
			throws IOException {
		this.client = aclient;
		this.out = new DataOutputStream(client.getOutputStream());
		this.in = new DataInputStream(client.getInputStream());
		this.initQuestion = new InitQuestion();
		this.classification = OC;
		this.ArrayListplays = ArraListplays;
		this.id = id;
		this.controlQuestion = initQuestion.getArrayListQuestions().size();
		this.play = getPlay();
		this.start();
	}

	public void sendClassification() throws Throwable {
		// classification.addArray(ArrayListplays.getPlays());
		System.out.println(classification.getPosition());
		out.writeUTF(classification.getPosition());
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
			client.close();
			e.printStackTrace();
		}
	}

	public boolean checkingNameUserValid(String name) {
		if (ArrayListplays.getPlays().size() > 0) {
			for (Play play : ArrayListplays.getPlays()) {
				if (play.getNameUser() != null) {
					if (play.getNameUser().equals(name))
						return false;
				}
			}
		}
		return true;
	}

	public void addNameUser() throws Throwable {
		try {
			data = in.readUTF();
			datas = data.split(" ");
			if (datas[0].equals("nameUser")) {
				if (checkingNameUserValid(datas[1])) {
					setPlayNameUser(datas[1]);
					// sleep(2000);
					warningBegin();
					this.hasUserName = true;
				} else {
					userNameWarning();
					setPlayNameUser(datas[1] + "0" + this.id);
					// sleep(2000);
					warningBegin();
					this.hasUserName = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e);
			e.printStackTrace();
		}
	}

	public void checkingNameUser() throws Throwable {
		if (!this.hasUserName) {
			addNameUser();
		}
	}

	public void checkingAnswer(String reply) throws Throwable {
		if (reply.equalsIgnoreCase(rightQuestion())) {
			addPoints();
			// sleep(1000);
			// out.writeUTF("Resposta Certa :)");
			// sleep(2000);
			// out.writeUTF("Proxima Pergunta");
			// sleep(2000);
		} else {
			controlQuestion();
			// sleep(2000);
			// out.writeUTF("Você errou :(");
		}
	}

	public String rightQuestion() {
		return initQuestion.getQuestion(this.idQuestion).getRightQuestion();
	}

	public void userNameWarning() throws Throwable {
		out.writeUTF("Nome de Usuário não é Válido, salvo como: " + datas[1] + "0" + this.id);
	}

	public void warningBegin() throws Throwable {
		out.writeUTF(getPlayNameUser() + " Vamos começar!?");
	}

	public void addPoints() throws Throwable {
		ArrayListplays.getPlay(this.id).setPoints(10);
		controlQuestion();
	}

	public String getPositionPlay(Play play) {
		return play.getPosition() + " " + play.getNameUser() + " " + play.getPoints();
	}

	public String getPlayPoints() {
		return String.valueOf((ArrayListplays.getPlay(this.id).getPoints()));
	}

	public String getPlayNameUser() {
		return ArrayListplays.getPlay(this.id).getNameUser();
	}

	public Play getPlay() {
		return ArrayListplays.getPlay(this.id);
	}

	public void setPlayNameUser(String nameUser) {
		ArrayListplays.getPlay(this.id).setNameUser(nameUser);
	}

	public void closeConnection() throws Throwable {
		this.stop();
		client.close();
	}

	public void controlQuestion() throws Throwable {
		if (controlQuestion > 1) {
			controlQuestion--;
			idQuestion++;
		} else {
			out.writeUTF("Muito bem " + getPlay().getNameUser() + ", Seus pontos foram " + getPlayPoints());
			sendClassification();
			closeConnection();
		}
	}

	public boolean verifica(String nome) {

		return true;
	}

	public void sleep(int timeMilins) throws InterruptedException {
		currentThread();
		Thread.sleep(timeMilins);
	}

	public void run() {
		while (true) {
			try {
				checkingNameUser();
				sleep(2000);
				sendQuestion();
			} catch (Throwable e) {
				try {
					this.stop();
					client.close();
				} catch (IOException e1) {
					// e1.printStackTrace();
				}
			}
		}
	}
}
