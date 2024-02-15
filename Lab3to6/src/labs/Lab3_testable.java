package labs;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import labs.Kattio;

public class Lab3_testable {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		// System.out.println(nToBaseTen("123M23", 36));
		// System.out.println(numberToBaseM(63993963, 36));
		
		Integer N = 32;
		Integer M = 17;

		// Proceed by reading A and B
		String A = "HEJ";
		String B = "HOPP";
		
		System.out.println(doCalculations(A, B, N, M));

		/*
		while (io.hasMoreTokens()) {
			
			
			Integer N = io.getInt();
			Integer M = io.getInt();

			// Proceed by reading A and B
			String A = io.getWord();
			String B = io.getWord();
			
			System.out.println(doCalculations(A, B, N, M));
		
			
			
			
			
			

		}*/

	}

	private static Long nToBaseTen(String number, Integer N) {
		//return Integer.parseInt(number, N);
		return Long.parseLong(number, N);
	}

	private static String numberToBaseM(Long number, Integer M) {
		//return Integer.toString(number, M);
		return Long.toString(number, M);
	}
	
	private static String doCalculations(String A, String B, Integer N, Integer M) {
		Long AbaseTen = nToBaseTen(A, N);
		Long BbaseTen = nToBaseTen(B, N);
		Long CbaseTen = AbaseTen * BbaseTen;
		//System.out.println(AbaseTen);
		//System.out.println(BbaseTen);
		//System.out.println(CbaseTen);
		
		
		return numberToBaseM(CbaseTen, M);
		
	}

}
