package AlgCompPack;

import java.util.ArrayList;

public class two_c_extra {
	private static Integer n = 640;
	private static Integer k = 236;
	private static Double p = 0.42;
	private static Double[] cache;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		cache = new Double[n + 1];

		// And call the function
		System.out.println(winStreak(n));

		

		while (io.hasMoreTokens()) {

			// We read the input
			n = io.getInt();
			k = io.getInt();
			p = io.getDouble();

			// Create a cache with room for all values. It will hold value null at every
			// position.
			cache = new Double[n + 1];

			// And call the function
			System.out.println(winStreak(n));

		}

	}

	private static Double winStreak(Integer x) {
			if(x<k) {return 0.0;}
			else if (x==k) {return Math.pow(p, k);}
			else { 
				if(cache[x]==null) {
					cache[x] = (winStreak(x-1) + Math.pow(p, k)*(1-p)*(1-winStreak(x-k-1)));
				}
				return cache[x];
			}
				
			
	}

}
