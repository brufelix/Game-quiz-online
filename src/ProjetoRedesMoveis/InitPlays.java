package ProjetoRedesMoveis;

import java.util.ArrayList;

public class InitPlays {
	ArrayList<Play> plays = new ArrayList<Play>();

	public InitPlays() {
		Play play01 = new Play("Thiago", 10);
		plays.add(play01);
		Play play02 = new Play("João", 30);
		plays.add(play02);
		Play play03 = new Play("Silmara", 20);
		plays.add(play03);
		Play play04 = new Play("Antonio", 40);
		plays.add(play04);
		Play play05 = new Play("Laura", 60);
		plays.add(play05);
	}

	public ArrayList<Play> getArray() {
		return plays;
	}
}
