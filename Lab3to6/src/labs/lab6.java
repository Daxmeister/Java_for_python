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
			replaceIfSameAndBetter(new RekordData(veggie, country, sizeOfVeggie, unit), dataPointsArray);

		}
		Collections.sort(dataPointsArray);
		for (RekordData i : dataPointsArray) {
			System.out.println(i.getPrintable());

		}

	}

	private static void replaceIfSameAndBetter(RekordData newData, List<RekordData> list) {
		// Check if we already have a datapoint for that country and veggie
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).isSame(newData))
				{
				if (newData.compareTo(list.get(i)) < 0)
					{// If our new data is BIGGER
						list.remove(i);
						list.add(newData);
						return;
					}
				else {//If our new data is smaller
					return;
					}
				}

		}
		// If we reach this point, then this is the first data entry for that veggie and country
		list.add(newData);
		return;

	}
}
