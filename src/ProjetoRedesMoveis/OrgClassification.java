package ProjetoRedesMoveis;

import java.util.ArrayList;

public class OrgClassification {
	ArrayList<Play> array = null;
	ArrayList<Play> helpArray = null;
	ArrayList<String> arrayNames = new ArrayList<String>();

	public OrgClassification() {
		InitPlays init = new InitPlays();
		this.array = init.getArray();
	}

	public boolean checkName(String name) {
		for (String play : arrayNames) {
			if (play != null) {
				if (play.equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public void addArray(ArrayList<Play> array) {
		this.array = array;
	}

	public String getPosition() {
		String position = "";
		for (int i = 0; i < array.size(); i++) {
			position += " " + returnPosition();
		}
		return position;
	}

	public boolean isBigger(Play play) {
		for (Play plays : array) {
			if (plays != null) {
				if (plays.getPoints() > play.getPoints()) {
					return false;
				}
			}
		}
		return true;
	}

	public void addArrayName(String name) {
		arrayNames.add(name);
	}

	public String returnPosition() {
		// System.out.println(array.toString());
		String allPosition = "";
		int position = 0;
		for (Play player : array) {
			if (player != null) {
				if (!checkName(player.getNameUser())) {
					if (isBigger(player)) {
						position++;
						allPosition += " " + position + " " + player.getNameUser() + " " + player.getPoints();
						player.setPosition(position);
						System.out.println("posotion:" + player.getPosition());
						arrayNames.add(player.getNameUser());
						array.remove(player);
					}
				} else {
					continue;
				}
			}
		}
		return allPosition;
	}
}
