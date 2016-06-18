package util;

public class Town {

	private char name;

	private Neighbour neighbour;

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public void setNeighbour(Neighbour neighbour) {
		this.neighbour = neighbour;
	}

	public Town(char name, Neighbour neighbour) {
		this.name = name;
		this.neighbour = neighbour;

	}

	public Neighbour getNeighbour() {

		return neighbour;
	}

}
