package AlgCompPack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class one_c_time {
    private static Integer a = 5;
    private static Integer b = 6;
    private static Integer c = 7;       
    private static ArrayList<Integer> cache = new ArrayList<Integer>();



    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
        Integer i = 170000000;
        System.out.println(i);
		
		Instant start = Instant.now();
		Integer n = i;
		cache = new ArrayList<Integer>();
		for (int j = 0; j < n+1; j++) {
            cache.add(-1);
        }
		coinMem(n);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));
        
        
        /*for (int i = 100000; i < 100000 ; i=i+20) {
			System.out.println(i);
			
			Instant start = Instant.now();
			Integer n = i;
			cache = new ArrayList<Integer>();
			for (int j = 0; j < n+1; j++) {
                cache.add(-1);
            }
			coinMem(n);
			Instant end = Instant.now();
			System.out.println(Duration.between(start, end));
		}*/
        
       

    }

    private static Integer coinMem(Integer n1) {
        
        // If we return a negative n or an n of 0 then we don't make any extra function calls. 
        // We can return this value directly, without using the cache.
        
        if (n1<0) {
            return 99999;
        } else if (n1 == 0) {
            return 0;}
        
        // Check if we have previously calculated this the coin-function of this n value
            else if (cache.get(n1) == -1) {     
                
                cache.set(n1, Math.min(Math.min(n1, 1+coinMem(n1-a)), Math.min(1+coinMem(n1-b), 1+coinMem(n1-c))));
            }
        
        
        
        return cache.get(n1);
            
    }
}
