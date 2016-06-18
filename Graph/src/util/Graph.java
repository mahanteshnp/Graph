package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/* 
 * This class stores the graph in a adjacency matrix using array list. 
 * Array list stores the node object which in turn holds the reference to its 
 * neighbours.
 * Hashmap maps the town name ie., character with the index of arraylist it is 
 * stored in.
 */
public class Graph {
     
	private ArrayList<Town> towns = new ArrayList<Town>();
	private HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	/*
	 * Takes the different commutes as the String input seperated by comma, Ex:
	 * "AB5, BC4, CD8, CA4 " and creates an arraylist of towns refrencing their
	 * neighbours.
	 */

	public Graph(String commute) {
		String[] paths = commute.split(",");

		for (String route : paths) {
			int i = 0;
			while (route.charAt(i) == ' ') {
				i++;

			}
			char source = route.charAt(i);
			char dest = route.charAt(i + 1);
			int distance = Character.getNumericValue(route.charAt(i + 2));
			System.out.println(source + "--" + dest + "--" + distance);

			int sourceIndex = townIndex(source);
			int destIndex = townIndex(dest);
			Neighbour newfound = new Neighbour(destIndex, distance, towns.get(sourceIndex).getNeighbour());
			towns.get(sourceIndex).setNeighbour(newfound);

		}

		System.out.println(towns);

	}
	
	

	public void displayGraph() {
		for (Town town : towns) {
			System.out.print(town.getName());
			Neighbour nbr = town.getNeighbour();
			while (nbr != null) {
				System.out.print("->");
				System.out.println(nbr.getDistanceFromTown() + "-" + nbr.getTownIndex());
				nbr = nbr.getNeighbour();
			}

		}

	}

	/*
	 * Returns the index of the town mapped in the graph. If the town is not
	 * found adds it and returns the index.
	 * 
	 */
	public int townIndex(char source) {
		int index = 0;

		for (Town town : towns) {
			if (town.getName() == source) {
				return towns.indexOf(town);
			}
		}

		Town newTown = new Town(source, null);
		towns.add(newTown);
		map.put(source, towns.indexOf(newTown));
		return towns.indexOf(newTown);
	}

	/*
	 * Takes the String as the route and prints the total distance if the given
	 * route exists or else prints 'NO SUCH ROUTE'.
	 */

	public void routeDistance(String route) {
		boolean routeExists = true;
		int distance = 0;
		char[] town = route.toCharArray();

		for (int i = 0; i < town.length - 2; i++) {
			int sourceIndex = map.get(town[i]);
			i++;
			while (town[i] == '-') {
				i++;
			}
			int destIndex = map.get(town[i]);
			i--;

			Town source = towns.get(sourceIndex);
			Neighbour dest = source.getNeighbour();

			while (dest != null) {
				if (dest.getTownIndex() == destIndex) {
					distance = distance + dest.getDistanceFromTown();
					routeExists = false;
					break;
				}
				dest = dest.getNeighbour();

			}

			if (dest == null) {
				System.out.println("NO SUCH ROUTE");

				break;
			}

		}
		if (routeExists) {
			System.out.println(distance);
		}

	}
	
	
	
	
	/*
	 * Takes the source and destination as characters and 
	 */
	
	

	public void noOfTrips(char source, char dest, int stops) {
		int count = 0;
		int sourceIndex = map.get(source);
		Town sourceTown = towns.get(sourceIndex);
		Queue<Town> queue = new LinkedList<Town>();
		int j = 1;
		queue.add(sourceTown);
		while (!queue.isEmpty()) {
			int size = queue.size();

			if (j == stops) {
				for (int i = 0; i < size; i++) {
					Town temp = queue.poll();
					Neighbour nbr = temp.getNeighbour();
					while (nbr != null) {
						int index = nbr.getTownIndex();

						if (towns.get(index).getName() == dest) {
							count++;
						}
						nbr = nbr.getNeighbour();
					}
				}
			} else {
				for (int i = 0; i < size; i++) {
					Town temp = queue.poll();
					Neighbour nbr = temp.getNeighbour();
					while (nbr != null) {
						int index = nbr.getTownIndex();

						queue.add(towns.get(index));
						nbr = nbr.getNeighbour();
					}
				}
			}
			j++;
		}

		System.out.println(count);

	}
	
	
	

	public void noOfTripsWithMaxStops(char source, char dest, int maxStops) {
		int count = 0;
		int sourceIndex = map.get(source);
		Town sourceTown = towns.get(sourceIndex);
		Queue<Town> queue = new LinkedList<Town>();
		int j = 1;
		queue.add(sourceTown);
		while (!queue.isEmpty()) {
			int size = queue.size();

			if (j == maxStops) {
				for (int i = 0; i < size; i++) {
					Town temp = queue.poll();
					Neighbour nbr = temp.getNeighbour();
					while (nbr != null) {
						int index = nbr.getTownIndex();

						if (towns.get(index).getName() == dest) {
							count++;
						}
						nbr = nbr.getNeighbour();
					}
				}
			} else {
				for (int i = 0; i < size; i++) {
					Town temp = queue.poll();
					Neighbour nbr = temp.getNeighbour();
					while (nbr != null) {
						int index = nbr.getTownIndex();
						if (towns.get(index).getName() == dest) {
							count++;
						}
						queue.add(towns.get(index));
						nbr = nbr.getNeighbour();
					}
				}
			}
			j++;
		}

		System.out.println(count);

	}

	public void differentPaths(char source, char dest, int dist) {
		int sourceIndex = map.get(source);
		int destIndex = map.get(dest);
		// Integer count=0;

		int paths = differentPaths(sourceIndex, dest, dist);

		System.out.println(paths);
	}

	public int differentPaths(int sourceIndex, char dest, int dist) {
		int count = 0;
		Town temp = towns.get(sourceIndex);
		Neighbour nbr = temp.getNeighbour();
		if (nbr == null) {
			return count;
		}

		while (nbr != null) {
			int diff = dist - nbr.getDistanceFromTown();
			if (diff >= 0) {
				Town destination = towns.get(nbr.getTownIndex());
				if (destination.getName() == dest) {
					count++;
					count = count + differentPaths(nbr.getTownIndex(), dest, diff);
				} else {
					count = count + differentPaths(nbr.getTownIndex(), dest, diff);
				}

			}
			nbr = nbr.getNeighbour();
		}

		return count;
	}

	/*
	 * This class is used to hold the total distance travelled in a particular
	 * path.
	 * 
	 */
	class Path {

		int weight;
		Neighbour nbr;

		public Path(int weight, Neighbour nbr) {
			this.weight = weight;
			this.nbr = nbr;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public Neighbour getNbr() {
			return nbr;
		}

		public void setNbr(Neighbour nbr) {
			this.nbr = nbr;
		}

	}

	public void shortestPath(char source, char dest) {
		int shortDist = Integer.MAX_VALUE;
		Queue<Path> queue = new LinkedList<Path>();
		int sourceIndex = map.get(source);
		Town town = towns.get(sourceIndex);
		Neighbour nbr = new Neighbour(map.get(source), 0, null);
		Graph.Path path = this.new Path(nbr.getDistanceFromTown(), nbr);

		queue.add(path);
		int i = 0;

		while (!queue.isEmpty()) {
			Path temp = queue.poll();

			Neighbour neibr = temp.getNbr();
			Town destTown = towns.get(neibr.getTownIndex());
			Neighbour nextnbr = destTown.getNeighbour();
			if (i != 0) {
				if (destTown.getName() == dest) {
					if (shortDist > temp.getWeight()) {
						shortDist = temp.getWeight();
					}
					continue;
				}
			}
			i++;
			while (nextnbr != null) {

				int dist = nextnbr.getDistanceFromTown() + temp.getWeight();

				queue.add(new Path(dist, nextnbr));

				nextnbr = nextnbr.getNeighbour();

			}

		}
		System.out.println(shortDist);
	}

}
