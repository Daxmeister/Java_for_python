package labs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class lab5_implicit_recursion {

    private static TreeSet<Character> foundPathsForLetter = new TreeSet<>(); // Contains all letters with paths
    private static Set<CustomPair> visitedCoordinates = new HashSet<>();
    private static char currentChar;
    private static char[][] inputMatrix;
    private static char[] capitalLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time

        while (io.hasMoreTokens()) {
            // Read input
        	int rows = io.getInt();
            int columns = io.getInt();
            
            // Turn input to a matrix
            inputMatrix = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String rowString = io.getWord();
                for (int j = 0; j < columns; j++) {
                    inputMatrix[i][j] = rowString.charAt(j);
                }
            }
            
            // For each letter, search for paths
            for (char letter : capitalLetters) {
                currentChar = letter;
                
                // Create a new matrix with numbers that correlate to whether it is a starting point, end point
                // Possible path or a "road block" ie. different letter
                int[][] intMatrix = createCharMatrix(letter);
                visitedCoordinates.clear(); // Reset visited coordinates for each new letter
                
                // Identify all possible startingpoints ie. top row letters
                ArrayList<CustomPair> startingPoints = findStaringPoints(intMatrix);
                
                // Search for a path, starting in each possible point
                for (CustomPair startingPoint : startingPoints) {
                    pathfinder(startingPoint, intMatrix);
                }
            }

            // Print the results
            if (foundPathsForLetter.isEmpty()) {
                System.out.println("0");
            } else {
                System.out.println(foundPathsForLetter.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            }
        }
    }

    private static void pathfinder(CustomPair coordinates, int[][] intMatrix) {
    	// Add starting point to a queue
        Queue<CustomPair> queue = new ArrayDeque<>();
        queue.offer(coordinates);

        // Work your way through the queue
        while (!queue.isEmpty()) {
        	// Take out the first element
            CustomPair currentCoordinates = queue.poll();
            int currentInt = intAtCoordinate(intMatrix, currentCoordinates);
             // Check that the current coordinate has not been visited before and that it is not a road block
            if (visitedCoordinates.contains(currentCoordinates) || currentInt == 0) {
                continue;
            } else { // If it is new and a correct letter...
                if (currentInt == 2) {
                    foundPathsForLetter.add(currentChar);
                    break;
                } else {
                    visitedCoordinates.add(currentCoordinates);

                    addIfInMatrix(new CustomPair(currentCoordinates.key1() - 1, currentCoordinates.key2()), intMatrix,
                            queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1() + 1, currentCoordinates.key2()), intMatrix,
                            queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1(), currentCoordinates.key2() - 1),
                            intMatrix, queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1(), currentCoordinates.key2() + 1),
                            intMatrix, queue);
                }
            }
        }
    }

    private static int intAtCoordinate(int[][] matrix, CustomPair coordinates) {
        int rowCoord = coordinates.key1();
        int colCoord = coordinates.key2();
        return matrix[rowCoord][colCoord];
    }

    private static void addIfInMatrix(CustomPair coordinates, int[][] intMatrix, Queue<CustomPair> queue) {
        int rowCoord = coordinates.key1();
        int colCoord = coordinates.key2();

        if (rowCoord >= 0 && rowCoord < intMatrix.length && colCoord >= 0 && colCoord < intMatrix[0].length) {
            queue.offer(coordinates);
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
        int numRows = inputMatrix.length;
        int numCols = inputMatrix[0].length;

        int[][] intMatrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (inputMatrix[i][j] == letter) {
                    if (i == 0) {
                        intMatrix[i][j] = 1;
                    } else if (i == numRows - 1) {
                        intMatrix[i][j] = 2;
                    } else {
                        intMatrix[i][j] = 3;
                    }
                }
            }
        }

        return intMatrix;
    }
}
