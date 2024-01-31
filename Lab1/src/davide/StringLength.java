package davide;

public class StringLength {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		String ord_str = "0123456789ABCDEGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz";
		Integer max_count = 0;

		while (io.hasMoreTokens()) {

			String row = io.getWord(); // reads input
			char[] row_array = row.toCharArray(); // converts string input into iterable character array

			Integer counter = 0;
			Character current_character = null;
			Character previous_character = null;

			for (int i = 0; i < row_array.length; i++) {
				current_character = row_array[i];

				// If we are starting a new streak of words
				if (previous_character == null) {
					counter = 1;
					
				} else {// If the current character is greater than or equal to the previous one
					if (ord_str.indexOf(current_character) >= ord_str.indexOf(previous_character)) {
						counter += 1;
						
					} else {
						// If we break the streak, reset counter. 
						counter = 1;
					}

				}
				// Finally, move on to next character and check if counter record was broken
				previous_character = current_character;
				if (counter > max_count) {max_count = counter;}

			}

			// We print the highest streak
			System.out.println(max_count);

		}
		io.close();
	}

}
