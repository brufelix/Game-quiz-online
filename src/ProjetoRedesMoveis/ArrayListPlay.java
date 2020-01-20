package ProjetoRedesMoveis;

import java.util.ArrayList;

public class ArrayListPlay {
	ArrayList<Play> plays = new ArrayList<Play>();

	public void add(Play play) {
		plays.add(play);
	}

	public Play getPlay(String nameUser) {
		for (Play play : plays) {
			if (play.getNameUser().equals(nameUser))
				return play;
		}
		return null;
	}

	public Play getPlay(int id) {
		for (Play play : plays) {
			if (play.getId() == id)
				return play;
		}
		return null;
	}

	public ArrayList<Play> getPlays() {
		return plays;
	}

	public void setPlays(ArrayList<Play> plays) {
		this.plays = plays;
	}

	@Override
	public String toString() {
		return "ArrayListPlay [plays=" + plays + "]";
	}
}
