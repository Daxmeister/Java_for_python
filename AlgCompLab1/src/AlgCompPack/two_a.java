package AlgCompPack;

import java.util.ArrayList;

public class two_a {
	private static Integer n = 4;
	private static Integer k = 2;
	private static Double p = 0.7;
	private static Double[][] cache;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		while (io.hasMoreTokens()) {

			// We read the input
			n = io.getInt();
			k = io.getInt();
			p = io.getDouble();

			// Create a cache with room for all values. It will hold value null at every
			// position.
			cache = new Double[n + 1][k + 1];

			// And call the function
			System.out.println(winStreak(n, k));

		}

	}

	private static Double winStreak(Integer x, Integer y) {

		if (y == 0) {
			return 1.0;
		} else if (x == 0 && y > 0) {
			return 0.0;
		}

		// Checks if the value has been calculated before, if not calculate.
		else {
			if (cache[x][y] == null) {
				cache[x][y] = (p * winStreak(x - 1, y - 1) + (1 - p) * (winStreak(x - 1, k)));
			}
			// Then returns the value
			return cache[x][y];
		}

	}

}
