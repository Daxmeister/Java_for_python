package AlgCompPack;

import java.util.ArrayList;

public class one_c {
    private static Integer a = 2;
    private static Integer b = 3;
    private static Integer c = 4;       
    private static ArrayList<Integer> cache = new ArrayList<Integer>();



    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
    
        while (io.hasMoreTokens()) {
            Integer n = io.getInt();
            for (int i = 0; i < n+1; i++) {
                cache.add(-1);
            }
            
            a = io.getInt();
            b = io.getInt();
            c = io.getInt();
            System.out.println(coinMem(n));

        }

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
