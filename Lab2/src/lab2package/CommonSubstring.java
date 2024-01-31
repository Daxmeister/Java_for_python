package lab2package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lab2package.Kattio;

public class CommonSubstring {
	private static final String ORDSTR = "0123456789ABCDEGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz";

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time

		while (io.hasMoreTokens()) {
			Integer n = io.getInt();
			String inputStr = io.getWord();

			Map<String, AtomicInteger> subStrMap = createMapOfSubstringCounts(n, inputStr);
			// System.out.println("subStrMap:" + subStrMap);
			ArrayList<String> longestSubstrings = getListOfLongestSubstrings(subStrMap);

			// System.out.println("longestSubstrings:" + longestSubstrings);

			System.out.println(getAlphabeticallyFirstString(longestSubstrings));

		}

	}

	private static Map<String, AtomicInteger> createMapOfSubstringCounts(Integer n, String inputStr) {
		Integer s = inputStr.length();
		Map<String, AtomicInteger> subStrMap = new HashMap<>();

		// Moves a window along the string, adding every substring to a hashmap with the
		// count as value
		for (int i = 0; i < s - n + 1; i++) {
			String subStr = inputStr.substring(i, i + n);
			subStrMap.putIfAbsent(subStr, new AtomicInteger(0));
			subStrMap.get(subStr).incrementAndGet();

		}

		return subStrMap;
	}

	private static ArrayList<String> getListOfLongestSubstrings(Map<String, AtomicInteger> inputMap) {
		// Method that takes a hashmap with substrings and their counts
		// and returns an ArrayList with all the substrings that are most common
		Integer maxLength = 0;
		ArrayList<String> longestSubstrings = new ArrayList<String>();

		for (Map.Entry<String, AtomicInteger> entry : inputMap.entrySet()) {
			String key = entry.getKey();
			AtomicInteger val = entry.getValue();
			
			// If we found a most common substring, empty the list, add the new substring
			// and update the maxvalue
			if (val.intValue() > maxLength) { 

				longestSubstrings = new ArrayList<String>();
				longestSubstrings.add(key);
				maxLength = val.intValue();

			// If it is not more common, add it if tied for most common.
			} else {
				if (val.intValue() == maxLength) {
					longestSubstrings.add(key);
				}
			}

		}

		return longestSubstrings;

	}

	private static String getAlphabeticallyFirstString(ArrayList<String> substringArray) {
		Collections.sort(substringArray);

		return substringArray.get(0);
	}

}
