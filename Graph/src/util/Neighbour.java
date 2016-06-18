package util;

public class Neighbour {

	private int townIndex;
	private int distanceFromTown;
	private Neighbour neighbour;

	public Neighbour(int townIndex, int distanceFromTown, Neighbour neighbour) {
		this.townIndex = townIndex;
		this.distanceFromTown = distanceFromTown;
		this.neighbour = neighbour;
	}

	public Neighbour(int distanceFromTown, Neighbour neighbour) {

		this.distanceFromTown = distanceFromTown;
		this.neighbour = neighbour;
	}

	public int getTownIndex() {
		return townIndex;
	}

	public void setTownIndex(int townIndex) {
		this.townIndex = townIndex;
	}

	public int getDistanceFromTown() {
		return distanceFromTown;
	}

	public void setDistanceFromTown(int distanceFromTown) {
		this.distanceFromTown = distanceFromTown;
	}

	public Neighbour getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(Neighbour neighbour) {
		this.neighbour = neighbour;
	}
}
