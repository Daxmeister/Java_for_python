package labs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import labs.Kattio;

public class lab6 {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		List<RekordData> dataPointsArray = new ArrayList<RekordData>(); 

		while (io.hasMoreTokens()) {

			String veggie = io.getWord();
			String country = io.getWord();
			int sizeOfVeggie;
			
			while (true) { // We don't know the length of the country, check until number
				String nextWord = io.getWord();
				try {
					sizeOfVeggie = Integer.parseInt(nextWord);
					break;
				} catch (NumberFormatException e) {
					country = country + " " + nextWord;
				}
			}
			
			String unit = io.getWord(); // Discard unit of measurement
			dataPointsArray.add(new RekordData(veggie, country, sizeOfVeggie, unit));
			
			

		}
		Collections.sort(dataPointsArray);
		for (RekordData i : dataPointsArray) {
			System.out.println(i.getPrintable());

		}
		
	}
}
