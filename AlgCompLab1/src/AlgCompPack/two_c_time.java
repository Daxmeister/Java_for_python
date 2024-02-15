package AlgCompPack;

import java.time.Duration;
import java.time.Instant;

public class two_c_time {
	private static int n = 640;
	private static int k = 236;
	private static double p = 0.8;
	private static double[] cache;

	public static void main(String[] args) {
		// We read the input

		int i=7000000;
		System.out.println(i);
		Instant start = Instant.now();
		n = i;
		k = n / 2;
		p = 0.99;

		// Create a cache with room for all values. It will hold value 0.0 at every
		// position.
		cache = new double[n + 1];

		// And call the function
		winStreak(n);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));

	}

	private static double winStreak(int x) {
		if (x < k) {
			return 0.0;
		} else if (x == k) {
			return Math.pow(p, k);
		} else {
			if (cache[x] == 0.0) {
				cache[x] = (winStreak(x - 1) + Math.pow(p, k) * (1 - p) * (1 - winStreak(x - k - 1)));
			}
			return cache[x];
		}

	}

}
