package codingTest;

import util.Graph;

public class Commuter {

	public static void main(String[] args) {
		Graph graph = new Graph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 ");

		System.out.println();
		System.out.print("Route Distance for  A-E-D" + "--");
		graph.routeDistance("A-E-D");
		System.out.println();
		System.out.print("Nuber of trips between C and C with Maximum 3 steps " + "--");
		graph.noOfTripsWithMaxStops('C', 'C', 3);
		System.out.println();
		System.out.print("different paths between C - C with distance less then or equal to 28" + "---");
		graph.differentPaths('C', 'C', 28);
		System.out.println();
		System.out.print("distance of shortest path between A and C" + "--");
		graph.shortestPath('A', 'C');
	}

}
