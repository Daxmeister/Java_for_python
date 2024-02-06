package AlgCompPack;

import java.math.BigDecimal;
import java.util.ArrayList;

public class two_c {
	private static Integer n = 20;
	private static Integer k = 4;
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
			else if (x==k) {
				BigDecimal bigP = BigDecimal.valueOf(p);
				return bigP.pow(k).doubleValue();
				}
			else {
				if(cache[n]==null) {
					BigDecimal a = BigDecimal.valueOf( winStreak(x-1));
					BigDecimal b = BigDecimal.valueOf( winStreak(x-k-1));
					BigDecimal bigP = BigDecimal.valueOf(p);
					BigDecimal pPowerK = bigP.pow(k);
					BigDecimal oneMinusP = bigP.negate().add(BigDecimal.valueOf(1.0));
					
					a.add(pPowerK.multiply(a));
					
					cache[n] = 
				}
				return cache[n];
			}
				
			
	}

}
