package davide;

public class StringLength {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Läser in en 
		String ord_str = "0123456789ABCDEGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz";
		Integer counter = 0;
		Integer max_count = 0;
		String current_character = null;
		String previous_character = null;
		
		
		while (io.hasMoreTokens()) {
			current_character = io.getWord();
			
			// If we are starting a new streak of words
			if (previous_character == null ) {
				counter += 1;
			}
			
				
			else { 
				// If the current character is greater than or equal to the previous one
				if (ord_str.indexOf(current_character) >= ord_str.indexOf(previous_character)) {
				    counter += 1;
				}
			
				// If the current character is smaller than the previous one
				else {
					  counter = 0;					
			    }
			
		}
			// Finally, move on to next character
			previous_character = current_character;
			if (counter > max_count) {max_count = counter;}
		
		}
		io.close();
		// We print the highest streak
		System.out.println(max_count);
		
	}

}
