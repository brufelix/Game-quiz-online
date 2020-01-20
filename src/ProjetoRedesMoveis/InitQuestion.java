package ProjetoRedesMoveis;

import java.util.ArrayList;

public class InitQuestion {
	ArrayList<Question> questions = new ArrayList<Question>();

	public InitQuestion() {
		Question question01 = new Question("Qual a primeira camada do modelo OSI modificado?", "C", "(A) Transporte",
				"(B) Aplicação", "(C) Fisica", "(D) Enlace", 1);
		add(question01);
		Question question02 = new Question("Se comunica com a utilização de frame de dados, qual é esta camada?", "B",
				"(A) Rede", "(B) Enlace", "(C) Aplicação", "(D) Transporte", 2);
		add(question02);
		Question question03 = new Question("O que caracteriza o Broadcast?", "C", "(A) Maior desempenho na rede",
				"(B) Comunicação para somente um grupo de máquinas",
				"(C) Envio de dados para todos conectados em uma Rede", "(D) Redução de uso de dados", 3);
		add(question03);

		Question question04 = new Question("Em quantas camadas se divide o modelo de referência OSI? ", "A", "(A) 7",
				"(B) 5", "(C) 8", "(D) 3", 4);
		add(question04);

		Question question05 = new Question("Qual o protocolo de transporte é orientado a conexão? ", "B", "(A) UDP",
				"(B) TCP", "(C) SMP", "(D) SMTP", 5);
		add(question05);
	}

	public void add(Question q) {
		questions.add(q);
	}

	public Question getQuestion(int id) {
		for (Question question : questions) {
			if (question.getId() == id)
				return question;
		}

		return null;
	}

	public ArrayList<Question> getArrayListQuestions() {
		return questions;
	}
}
