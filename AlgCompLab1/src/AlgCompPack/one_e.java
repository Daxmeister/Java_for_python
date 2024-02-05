package AlgCompPack;

import java.util.ArrayList;

public class one_e {
	private static Integer a = 2;
	private static Integer b = 3;
	private static Integer c = 4;
	private static ArrayList<Integer> cache = new ArrayList<Integer>();

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		while (io.hasMoreTokens()) {
			// We read the input
			Integer n = io.getInt();

			a = io.getInt();
			b = io.getInt();
			c = io.getInt();

			// And call the function

			System.out.println(coinBot(n));

		}

	}

	private static Integer coinBot(Integer n1) {

		// Iterates up to the input value n1
		// Instead of recursively calling upon the function, this function starts with
		// the lowest n
		// and works it's way up. Calling upon previously stored values in the cache.

		for (int i = 0; i <= n1; i++) {

			cache.add(Math.min(Math.min(i, 1 + cleverCache(i - a)),
					Math.min(1 + cleverCache(i - b), 1 + cleverCache(i - c))));

		}

		return cache.get(n1);

	}

	private static Integer cleverCache(Integer n1) {
		// A method used to return the results from the cache, but it catches negative
		// numbers and 0. A very clever cache.
		if (n1 < 0) {
			return 99999;
		} else if (n1 == 0) {
			return 0;
		} else {
			return cache.get(n1);
		}

	}
}
