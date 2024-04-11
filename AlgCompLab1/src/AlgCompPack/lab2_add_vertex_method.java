package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.ByteArrayInputStream; // #######

public class lab2_add_vertex_method {

	private static int V;
	private static int E;
	private static int m;
	private static List<int[]> edgeArrayInteger = null;
    private static Set<Integer> verticesInEdges = new HashSet<Integer>();
	private static List<Integer> singleEdgesToRemove = new ArrayList<Integer>();
    private static StringBuilder outputString = new StringBuilder();


	public static void main(String[] args) {
		// ############################################################
		//String input = "6\n" + "2\n" + "2\n" + "1 3\n" + "2 3\n";
		//String input = "2\n" + "0\n" + "0\n";
		String input = "4\n" + "4\n" + "3\n" + "1 2\n" + "1 3\n" + "2 3\n" + "3 4\n";

		// Redirect input from a string
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		// ############################################################

		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		V = io.getInt();
		E = io.getInt();
		m = io.getInt();

		edgeArrayInteger = new ArrayList<int[]>(); // Used to save edges

		while (io.hasMoreTokens()) {

			int edgePoint1 = io.getInt();
			int edgePoint2 = io.getInt();
			verticesInEdges.add(edgePoint1);
			verticesInEdges.add(edgePoint2);
			edgeArrayInteger.add(new int[] { edgePoint1, edgePoint2 });
		}

		if ((m == 0 && V > 0) || (m == 1 && E > 0)) { // Insolvable, we feed it an insolvable problem
			System.out.println("2");
			System.out.println("1");
			System.out.println("2");
			// Con type 1
			System.out.println("1 1");
			System.out.println("1 2");
			// Con type 2
			System.out.println("2 1 2");
			System.out.println("2 1 2");

		} else {

			if (verticesInEdges.size() != V) {handleLonelyNodes();} // If there are lonely nodes, handle them

			int n = V + 3; // Number of roles (former nodes)
			int s = E + 2; // Number of scenes (former edges)
			int k = m + 3; // Number of actors (former colors)

			// We print n, s and k
			
			outputString.append(n).append("\n");
			outputString.append(s).append("\n");
			outputString.append(k).append("\n");
			//System.out.println(outputString.toString().trim());

			// We create the print out needed for Constraint type 1 #######################

			// We add roles representing our minimum cast

			//System.out.println("1 1");
			//System.out.println("1 2");
			//System.out.println("1 3");
			outputString.append("1 1").append("\n");
			outputString.append("1 2").append("\n");
			outputString.append("1 3").append("\n");

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
					//System.out.println(actorsForRole);
					outputString.append(actorsForRole).append("\n");

					
				}
			}

			// We finish by printing our constraints type 2 #################

			// We start by satisfying the minimum criteria with two scenes
			//System.out.println("2 1 3");
			//System.out.println("2 2 3");
			outputString.append("2 1 3").append("\n");
			outputString.append("2 2 3").append("\n");


			// And then print all other "scenes" ergo edges


			for (int[] edge : edgeArrayInteger) {
				outputString.append("2 " + (edge[0]+3) + " " + (edge[1]+3)).append("\n");

				//System.out.println("2 " + (edge[0]+3) + " " + (edge[1]+3)); // Start with the number of "roles" per scene which is 2
			} 
		System.out.println(outputString.toString().trim());
		}
		

	}

	private static void handleLonelyNodes() {
		V = V + 1; // Add one extra node that we will connect all lonely nodes to (bipartite)
		for (int i = 1; i <= V-1; i++) {
			// For every node except our newly created one, check if it is lonely
			if (!verticesInEdges.contains(i)) {  
				edgeArrayInteger.add(new int[] {i, V}); // Connect to newly created node
				E = E + 1; // Increase number of edges by one
			}
		}

	}

}
