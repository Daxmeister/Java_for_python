package labs;

import java.util.HashMap;
import java.util.Map;

public class lab5hashmap {

  public static void main(String[] args) {
    // Create a Map with a Pair record as key and a double as value
    Map<Pair, Double> cache = new HashMap<>();

    // Example entries
    Pair key1 = new Pair(1, 2);
    Pair key2 = new Pair(4, 5);
    Pair key2b = new Pair(4, 5);
    Pair key3 = new Pair(17, 42);

    cache.put(key1, 3.14);
    cache.put(key2, 6.28);

    // Accessing values
    System.out.println(cache.get(key1)); // Output: 3.14
    System.out.println(cache.get(key2)); // Output: 6.28

    cacheContainsKey(cache, key2);
    cacheContainsKey(cache, key2b);
    cacheContainsKey(cache, key3);

    System.out.println(key2 == key2b);
    System.out.println(key2.equals(key2b));
  }

  private static void cacheContainsKey(Map<Pair, Double> cache, Pair key) {
    if (cache.containsKey(key)) {
      System.out.println("Cache contains key: " + key);
    } else {
      System.out.println("Cache does NOT contain key: " + key);
    }
  }
}


record Pair(int key1, int key2) {
}
