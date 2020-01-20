package ProjetoRedesMoveis;

public class Play {
	private String nameUser;
	private String control;
	private int id, points, position, idQuestion;
	private boolean read, hasUserName = false;;

	public Play() {
		// this.id = 1;
		this.idQuestion = 1;
	}

	public Play(String string, int i) {
		this.nameUser = string;
		this.points = i;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String name) {
		this.nameUser = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points += points;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Play [nameUser=" + nameUser + ", id=" + id + ", points=" + points + ", position=" + position + "]";
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public boolean isHasUserName() {
		return hasUserName;
	}

	public void setHasUserName(boolean hasUserName) {
		this.hasUserName = hasUserName;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}
}
