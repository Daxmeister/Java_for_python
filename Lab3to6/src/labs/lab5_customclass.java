package labs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

import labs.Kattio;

public class lab5_customclass {

    private static TreeSet<Character> foundPathsForLetter = new TreeSet<>(); // Contains all letters with paths
    // Create a HashSet with coordiantes already visited used by recursion. Is reset
    // for every new letter
    private static Set<CustomPair> visitedCoordinates = new HashSet<>();
    private static char currentChar;

    // private static Map<CustomPair, Double> cache = new HashMap<>(); // used by
    // recursive function to see which paths have
    // already been checked, clear after each letter
    private static char[][] inputMatrix; // To save input matrix as 2d character array
    private static char[] capitalLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time

        while (io.hasMoreTokens()) {

            // We start by reading the input and creating a matrix from the input
            int rows = io.getInt();
            int columns = io.getInt();
            inputMatrix = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String rowString = io.getWord();
                for (int j = 0; j < columns; j++) {
                    inputMatrix[i][j] = rowString.charAt(j);
                }
            }

            for (char letter : capitalLetters) {
                currentChar = letter;
                int[][] intMatrix = createCharMatrix(letter);
                //System.out.println(currentChar);
                Set<CustomPair> visitedCoordinates = new HashSet<>(); // We reset it with every new character
                ArrayList<CustomPair> startingPoints = findStaringPoints(intMatrix); // We identify the coordinates of all
                //System.out.println(startingPoints);                                                               // starting points
                for (CustomPair startingPoint : startingPoints) {
                    pathfinder(startingPoint, intMatrix);
                }
                

            }
            
            // Lastly, we print our results
            if (foundPathsForLetter.isEmpty()) {System.out.println("0");}
                        else {System.out.println(foundPathsForLetter.stream().map(String::valueOf).collect(Collectors.joining(" ")));

                
            }

        }

    }

    private static void pathfinder(CustomPair coordinates, int[][] intMatrix) {
        int currentInt = intAtCoordinate(intMatrix, coordinates);
        //System.out.println(currentInt);
        if (visitedCoordinates.contains(coordinates) || currentInt == 0) {
            /* do nothing */ } else {
            if (currentInt == 2) { // We have arrived at intended position
                foundPathsForLetter.add(currentChar);
                //System.out.println(currentChar);
            } else {// Means that current char is 1 or 3
                visitedCoordinates.add(coordinates); // Append to visited coordinates
                // Calls all 4 possible adjacent coordinates
                callIfInMatrix(new CustomPair(coordinates.key1() - 1, coordinates.key2()), intMatrix);
                callIfInMatrix(new CustomPair(coordinates.key1() + 1, coordinates.key2()), intMatrix);
                callIfInMatrix(new CustomPair(coordinates.key1(), coordinates.key2() - 1), intMatrix);
                callIfInMatrix(new CustomPair(coordinates.key1(), coordinates.key2() + 1), intMatrix);

            }
        }

    }

    private static int intAtCoordinate(int[][] matrix, CustomPair coordinates) {
        int rowCoord = coordinates.key1();
        int colCoord = coordinates.key2();
        return matrix[rowCoord][colCoord];
    }

    private static void callIfInMatrix(CustomPair coordinates, int[][] intMatrix) {
        // Only calls the function if coordinates are not out of bounds
        int rowCoord = coordinates.key1();
        int colCoord = coordinates.key2();
        //System.out.println("" + rowCoord + colCoord);
        if (rowCoord >= 0 && rowCoord < intMatrix.length && colCoord >= 0 && colCoord < intMatrix[0].length) {
            //System.out.println("True!");
            pathfinder(coordinates, intMatrix);

        }
    }

    private static ArrayList<CustomPair> findStaringPoints(int[][] matrix) {
        ArrayList<CustomPair> startingPoints = new ArrayList<>();
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 1) {
                startingPoints.add(new CustomPair(0, j));
            }
        }
        return startingPoints;
    }

    private static int[][] createCharMatrix(char letter) {
        // Creates a matrix for a specific character
        int numRows = inputMatrix.length;
        int numCols = inputMatrix[0].length;

        // Create the empty matrix
        int[][] intMatrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // From here on, the code checks each letter
                if (inputMatrix[i][j] == letter) { // Since the default value is 0, we only care about correct chars

                    if (i == 0) { // The letters in the first row are staring points
                        intMatrix[i][j] = 1;
                    } else if (i == numRows - 1) { // The letters in the last row are targets
                        intMatrix[i][j] = 2;
                    } else { // All other letters are stepping stones for our path
                        intMatrix[i][j] = 3;
                    }
                }
            }
        }

        return intMatrix;

    }

    /*
     * private static void addIfNotPresent(String str) { // Adds new things to the
     * treestructure if (!sortedStrings.contains(str)) { sortedStrings.add(str); }
     * 
     * 
     * 
     * }
     */

}
