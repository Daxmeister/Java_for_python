package AlgCompPack;

import java.util.ArrayList;

public class one_e_testable {
	private static Integer a = 2;
	private static Integer b = 3;
	private static Integer c = 4;
	private static ArrayList<Integer> cache = new ArrayList<Integer>();

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		Integer n = 10;
	
		System.out.println(coinBot(n));

		while (io.hasMoreTokens()) {
			// Integer n = io.getInt();
			for (int i = 0; i < n + 1; i++) {
				cache.add(-1);
			}

			a = io.getInt();
			b = io.getInt();
			c = io.getInt();
			System.out.println(coinBot(n));

		}

	}

	private static Integer coinBot(Integer n1) {

		// If we return a negative n or an n of 0 then we don't make any extra function
		// calls.
		// We can return this value directly, without using the cache.

		// This is where we calculate each number in a bottom up way
		// First check if the number has been calculated before

		// If not then iterate through all numbers leading up to this one
		
		for (int i = 0; i <= n1; i++) {
				cache.add(Math.min(Math.min(i, 1 + cleverCache(i - a)),
						Math.min(1 + cleverCache(i - b), 1 + cleverCache(i - c))));
			}
		

		return cache.get(n1);

	}

	private static Integer cleverCache(Integer n1) {
		// A method used to return the results from the cache, but it catches negative
		// numbers and 0
		if (n1 < 0) {
			return 99999;
		} else if (n1 == 0) {
			return 0;
		} else {
			return cache.get(n1);
		}

	}
}
