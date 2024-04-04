package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.ByteArrayInputStream;

public class lab2_testable {

	public static void main(String[] args) {
		String input = "3\n" + "2\n" + "2\n" + "1 3\n" + "2 3\n";

		// Redirect input from a string
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		Kattio io = new Kattio(System.in, System.out);

		Integer V = io.getInt();
		Integer E = io.getInt();
		Integer m = io.getInt();

		List<String> edgeArray = new ArrayList<String>(); // Used to save edges

		while (io.hasMoreTokens()) {

			Integer edgePoint1 = 3 + io.getInt(); // We add 3 because we ignore the first three nodes that are just used to satisfy empty graph
			Integer edgePoint2 = 3 + io.getInt();
			String edge =  edgePoint1 + " " + edgePoint2;
			edgeArray.add(edge);
		}
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
		String actorsForRole = ""; // Since all roles can be played by all actors (every node could have any color) we can create just one print
		actorsForRole += m + " "; // We add the number of actors first

		for (int i = 1; i <= m; i++) {
			int q = i + 3; // Since we skip the first three actors
			actorsForRole += q + " ";
		}
		actorsForRole = actorsForRole.trim(); // Remove trailing space

		for (int i = 0; i < V; i++) { // We only print V times because we printed the first 3 roles before
			System.out.println(actorsForRole);
		}
		
		

		// We finish by printing our constraints type 2 #################

		// We start by satisfying the minimum criteria with two scenes
		System.out.println("2 1 3");
		System.out.println("2 2 3");

		// And then print all other "scenes" ergo edges

		for (String i : edgeArray) {
			System.out.println("2 " + i);
		} // Start with the number of "roles" per scene which is 2

		io.close();
	}
}
