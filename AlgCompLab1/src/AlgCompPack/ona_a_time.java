package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

public class ona_a_time {
	private static Integer a = 5;
	private static Integer b = 6;
	private static Integer c = 7;
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		
		for (int i = 100; i < 120; i=i+1) {
			System.out.println(i);
			Instant start = Instant.now();
			Integer n = i;
			coin(n);
			Instant end = Instant.now();
			System.out.println(Duration.between(start, end));
		}
		
		
	}
	
	private static Integer coin(Integer n1) {
		if (n1<0) {
			return 99999;
		} else if (n1 == 0) {
			return 0;
			
			
		} else {
			
			return Math.min(Math.min(n1, 1+coin(n1-a)), Math.min(1+coin(n1-b), 1+coin(n1-c)));
		}
			
	}
}
