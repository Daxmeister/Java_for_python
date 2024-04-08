package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.ByteArrayInputStream; // #######

public class lab2 {

	private static Integer V;
	private static Integer E;
	private static Integer m;
	private static List<String> edgeArray = null;
	private static List<Integer> edgeArrayInteger = null;

	private static List<Integer> singleEdgesToRemove = new ArrayList<Integer>();

	public static void main(String[] args) {
		// ############################################################
		String input = "6\n" + "2\n" + "2\n" + "1 3\n" + "2 3\n";
		// String input = "2\n" + "0\n" + "0\n";

		// Redirect input from a string
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		// ############################################################

		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		V = io.getInt();
		E = io.getInt();
		m = io.getInt();

		edgeArray = new ArrayList<String>(); // Used to save edges

		while (io.hasMoreTokens()) {

			Integer edgePoint1 = io.getInt(); 											
			Integer edgePoint2 = io.getInt();
			String edge = edgePoint1 + " " + edgePoint2;
			edgeArray.add(edge);
		}

		if (m == 0 && V > 0) { // Insolvable, we feed it an insolvable problem
			System.out.println("2");
			System.out.println("1");
			System.out.println("2");
			// Con type 1
			System.out.println("1 1");
			System.out.println("1 2");
			// Cont type 2
			System.out.println("2 1 2");
			System.out.println("2 1 2");

		} else { // This means that the either m>0 and V>0 OR m=0=V

			handleLonelyNodes(); // Handles any lonely nodes, since all roles must be in a scene.
			// It removes all lonely nodes and adjusts the edge array

			Integer n = V + 3; // Number of roles (former nodes)
			Integer s = E + 2; // Number of scenes (former edges)
			Integer k = m + 3; // Number of actors (former colors)

			// We print n, s and k
			System.out.println(n);
			System.out.println(s);
			System.out.println(k);

			// We create the print out needed for Constraint type 1 #######################

			// We add roles representing our minimum cast

			System.out.println("1 1");
			System.out.println("1 2");
			System.out.println("1 3");

			// And then print every other role which can be played by all actors except the
			// first 3
			if (m > 0) {
				String actorsForRole = "";
				actorsForRole += m + " "; // We add the number of actors

				for (int i = 1; i <= m; i++) {
					int q = i + 3; // Since we skip the first three actors
					actorsForRole += q + " ";
				}
				actorsForRole = actorsForRole.trim(); // Remove trailing space

				for (int i = 0; i < V; i++) { // We only print V times because we printed the first 3 roles before
					System.out.println(actorsForRole);
				}
			}

			// We finish by printing our constraints type 2 #################

			// We start by satisfying the minimum criteria with two scenes
			System.out.println("2 1 3");
			System.out.println("2 2 3");

			// And then print all other "scenes" ergo edges

			increaseEdgeValues(); // Since we inserted 3 roles in the start, we need to update edgeArray

			for (String i : edgeArray) {
				System.out.println("2 " + i);
			} // Start with the number of "roles" per scene which is 2

		}
	}

	private static void handleLonelyNodes() {
		// Iterate through through all integers
		Integer VStart = V;
		for (int i = 1; i <= VStart; i++) { // We check for every node, 1 through V, if it is part of an edge
			
			if (containsNumber(edgeArray, i)) { // If it is, then we do nothing
				// Do nothing
			
			} else { // If it is not part of any edge
					V = V - 1; // Take away one vertex
					singleEdgesToRemove.add(i); // We flag that we have to remove it
				
			}
		}
		
		// We then remove all nodes and adjust the edges. We start with the HIGHEST
		// vertex
		Collections.sort(singleEdgesToRemove, Collections.reverseOrder()); // Sort in reverse order
		for (Integer vertex : singleEdgesToRemove) {
			changeEdgeValues(vertex);

		}

	}

	public static boolean containsNumber(List<String> edgeArray, int m) {
		for (String edge : edgeArray) {
			String[] parts = edge.split(" ");
			for (String part : parts) {
				if (Integer.parseInt(part) == m) {
					return true;
				}
			}
		}
		return false;
	}

	public static void increaseEdgeValues() {
		// Increases the value of all vertexes by 3

		List<String> edgeArrayIncreased = new ArrayList<>();

		for (String edge : edgeArray) { // For every edge
			String[] parts = edge.split(" "); // Create list with the two values as strings
			Integer counter = 0;
			Integer[] newParts = new Integer[2]; // Create list to hold values as integers
			for (String part : parts) { // Convert both values to integers and increase by 3
				newParts[counter] = Integer.parseInt(part) + 3;

				counter += 1;
			}

			counter = 0; // Reset counter
			String stringSnippet = newParts[0] + " " + newParts[1]; // Append both values and create new sting
			edgeArrayIncreased.add(stringSnippet); // Store that string to the updated edgeArray
		}

		edgeArray = edgeArrayIncreased; // Replace edgeArray with the increased one
	}

	public static void changeEdgeValues(Integer vertexToBeRemoved) {
		// Takes an integer, will reduce all vertexes found in edges by one if they are
		// greater than that vertex

		for (Integer i=0; i< edgeArray.size(); i++) { // For every edge
			String edge = edgeArray.get(i);
			String[] parts = edge.split(" "); // Create list with the two values as strings
			Integer counter = 0;
			Integer[] newParts = new Integer[2]; // Create list to hold values as integers
			Boolean hasChanged = false;
			for (String part : parts) { // Convert both values to integers and increase by 3
				if (Integer.parseInt(part) > vertexToBeRemoved) { // Checks if the vertex comes after and should thus be
																	// decreased
					newParts[counter] = Integer.parseInt(part) - 1;
					hasChanged = true;
				}
				counter += 1;
			}

			counter = 0; // Reset counter
			if(hasChanged) { // If we made a change, adjust the edgeArray
			String stringSnippet = newParts[0] + " " + newParts[1]; // Append both values and create new sting

			edgeArray.set(i, stringSnippet); // Replace the value in the edgeArray
			}
		}
	}
}
