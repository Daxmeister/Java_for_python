package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class one_e_time {
	private static Integer a = 5;
	private static Integer b = 6;
	private static Integer c = 7;
	private static ArrayList<Integer> cache = new ArrayList<Integer>();

	public static void main(String[] args) {
		Integer i = 220000000;
		System.out.println(i);

		Instant start = Instant.now();
		Integer n = i;
		coinBot(n);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));


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
