package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class lab2 {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		Integer V = io.getInt(); 
		Integer E = io.getInt();
		Integer m = io.getInt();
		
		List<String> edgeArray = new ArrayList<String>(); // Used to save edges


		while (io.hasMoreTokens()) {

			String edge = io.getWord();
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
		
		// And then print every other role which can be played by all actors except the first 3
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
		
		
		String numbers = "";
        for (int i = 1; i <= n; i++) {
            numbers += i + " ";
        }
        numbers = numbers.trim(); // Remove trailing space
				
		// We finish by printing our constraints type 2 #################
        
        // We start by satisfying the minimum criteria with two scenes
        System.out.println("2 1 3");
        System.out.println("2 2 3");
        
        // And then print all other "scenes" ergo edges
		
		for (String i : edgeArray) {
			System.out.println("2 " + i);} // Start with the number of "roles" per scene which is 2
		
	}
}
