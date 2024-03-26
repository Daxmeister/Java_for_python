package labs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import labs.Kattio;

public class Lab4 {
	
	private static TreeSet<String> sortedStrings = new TreeSet<>();

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time
		
	
		
		while (io.hasMoreTokens()) {

			String inputString = io.getWord();
			char[] inputArray = inputString.toCharArray();
			
			// We call upon our recursive function that, step by step, reduces
			// the size of the input array and tries all options.
			permutator("", inputArray);
			
			// This will always print in correct order since that is how the tree stores strings
			for (String str : sortedStrings) {
	            System.out.println(str);
	        }
			

		}

	}
	
	private static void permutator(String word, char[] lettersLeft) {
		// If we have no letters left, then we are at the bottom of the recursion and we
		// try to add the word to our TreeStructure.
		if(lettersLeft.length == 0) {
			addIfNotPresent(word);
			return; // We break the function this way
		}
		
		for (int i=0; i<lettersLeft.length; i++) {
			//Iterates through the list so as to add every possible solution
		
			// Create list with all letters except at index i
			char[] newList = createNewList(lettersLeft, i); 
			
			// Create new word using old word and the letter we took out at index i
			String newWord = word + lettersLeft[i];
			
			// Use as input for the function recursively
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
		// Adds new things to the treestructure
        if (!sortedStrings.contains(str)) {
            sortedStrings.add(str);
        }

	

}
	
}
