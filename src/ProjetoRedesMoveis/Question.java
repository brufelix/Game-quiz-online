package ProjetoRedesMoveis;

public class Question {
	private String alternative01, alternative02, alternative03, alternative04, question, rightQuestion;
	private int id;

	public Question(String question, String rightQuestion, String alternative01, String alternative02,
			String alternative03, String alternative04, int id) {
		this.alternative01 = alternative01;
		this.alternative02 = alternative02;
		this.alternative03 = alternative03;
		this.alternative04 = alternative04;
		this.question = question;
		this.rightQuestion = rightQuestion;
		this.id = id;
	}

	public String getAlternative01() {
		return alternative01;
	}

	public void setAlternative01(String alternative01) {
		this.alternative01 = alternative01;
	}

	public String getAlternative02() {
		return alternative02;
	}

	public void setAlternative02(String alternative02) {
		this.alternative02 = alternative02;
	}

	public String getAlternative03() {
		return alternative03;
	}

	public void setAlternative03(String alternative03) {
		this.alternative03 = alternative03;
	}

	public String getAlternative04() {
		return alternative04;
	}

	public void setAlternative04(String alternative04) {
		this.alternative04 = alternative04;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRightQuestion() {
		return rightQuestion;
	}

	public void setRightQuestion(String rightQuestion) {
		this.rightQuestion = rightQuestion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
