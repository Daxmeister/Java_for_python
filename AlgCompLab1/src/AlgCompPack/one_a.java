package AlgCompPack;


public class one_a {
	private static Integer a = 0;
	private static Integer b = 0;
	private static Integer c = 0;
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		
		while (io.hasMoreTokens()) {
			Integer n = io.getInt();
			a = io.getInt();
			b = io.getInt();
			c = io.getInt();
			System.out.println(coin(n));
			
			

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
