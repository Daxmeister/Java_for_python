package labs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import labs.Kattio;

public class Lab4_testable {
	
	private static TreeSet<String> sortedStrings = new TreeSet<>();

	public static void main(String[] args) {
		
		String inputString = "ABA";
		char[] inputArray = inputString.toCharArray();
		permutator("", inputArray);

		
		
		for (String str : sortedStrings) {
            System.out.println(str);
        }
		
	}
	
	private static void permutator(String word, char[] lettersLeft) {
		if(lettersLeft.length == 0) {
			addIfNotPresent(word);
			return;
		}
		
		for (int i=0; i<lettersLeft.length; i++) {
			//Iterates through the list
		
			char[] newList = createNewList(lettersLeft, i);
			String newWord = word + lettersLeft[i];
			permutator(newWord, newList);
			
			}
		
		}
		
			
		
		
	
	
	private static char[] createNewList(char[] oldList, int indexToRemove) {
		// Takes a list and an index, returns a new list with that index removed. 
		// Index can be <oldList.length
		char[] newList = new char[oldList.length-1];
		int counter = 0;
		for(int i=0; i< oldList.length; i++) {
			if (i!= indexToRemove) {
				newList[counter] = oldList[i];
				counter ++;
	
			}
		}
		return newList;
		
	}
	
	private static void addIfNotPresent(String str) {
        if (!sortedStrings.contains(str)) {
            sortedStrings.add(str);
        }

	

}
	
}
