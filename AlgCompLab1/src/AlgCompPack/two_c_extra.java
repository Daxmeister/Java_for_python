package AlgCompPack;

public class two_c_extra {
	private static int n = 640;
	private static int k = 236;
	private static double p = 0.8;
	private static double[] cache;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		while (io.hasMoreTokens()) {

			// We read the input
			n = io.getInt();
			k = io.getInt();
			p = io.getDouble();

			// Create a cache with room for all values. It will hold value 0.0 at every
			// position.
			cache = new double[n + 1];

			// And call the function
			System.out.println(winStreak(n));

		}

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
