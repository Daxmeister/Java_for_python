package labs;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class lab5_testable {

    private static TreeSet<Character> foundPathsForLetter = new TreeSet<>(); // Contains all letters with paths
    private static char currentChar;
    private static char[][] inputMatrix  = {{'A', 'B'}};
         
    private static char[] capitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
       

        
            // Read input
            int rows = 1;
            int columns = 2;

            // Turn input to a matrix
            
            
            
            for (int i = 0; i < inputMatrix.length; i++) {
                for (int j = 0; j < inputMatrix[i].length; j++) {
                    System.out.print(inputMatrix[i][j]);
                }
                System.out.println();}

            // For each letter, search for paths
            for (char letter : capitalLetters) {
                currentChar = letter;


                // Identify all possible starting points, i.e., top row letters
                ArrayList<CustomPair> startingPoints = findStaringPoints(inputMatrix, rows);

                // Search for a path, starting at each possible point
                for (CustomPair startingPoint : startingPoints) {
                    pathfinder(startingPoint);
                }
            }

            // Print the results
            if (foundPathsForLetter.isEmpty()) {
                System.out.println("0");
            } else {
                System.out.println(foundPathsForLetter.stream().map(String::valueOf).collect(Collectors.joining("")));
            }
        
    }

    private static void pathfinder(CustomPair coordinates) {
        // Add starting point to a queue
        Queue<CustomPair> queue = new ArrayDeque<>();
        queue.offer(coordinates);
        inputMatrix[coordinates.key1()][coordinates.key2()] = 0; // We use inputMatrix to keep track of where we have been
        

        // Work your way through the queue
        while (!queue.isEmpty()) {
            // Take out the first element
            CustomPair currentCoordinates = queue.poll();

             { // If it is the correct letter...
                // If it is in the last row, add it to foundPathsForLetter and break out of the loop, we have 
            	// found a path for this letter
                if (currentCoordinates.key1() == inputMatrix.length - 1) {
                    foundPathsForLetter.add(currentChar);
                    break;
                } else { // Else, it is a stepping stone and the search continues..
                    

                    // Add adjacent coordinates to the queue
                    addIfInMatrix(new CustomPair(currentCoordinates.key1() - 1, currentCoordinates.key2()), queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1() + 1, currentCoordinates.key2()), queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1(), currentCoordinates.key2() - 1), queue);
                    addIfInMatrix(new CustomPair(currentCoordinates.key1(), currentCoordinates.key2() + 1), queue);
                }
            }
        }
    }

    private static void addIfInMatrix(CustomPair coordinates, Queue<CustomPair> queue) {
        int rowCoord = coordinates.key1();
        int colCoord = coordinates.key2();
        
        // Add if within the bounds of the matrix, if not visited before and it is not a 0. Checked in that order.
        if (rowCoord >= 0 && rowCoord < inputMatrix.length && colCoord >= 0 && colCoord < inputMatrix[0].length && inputMatrix[rowCoord][colCoord] != 0 && inputMatrix[rowCoord][colCoord] == currentChar) {
            queue.offer(coordinates);
            
            inputMatrix[rowCoord][colCoord] = 0; // We mark it as visited in the input matrix
        }
    }

    private static ArrayList<CustomPair> findStaringPoints(char[][] matrix, int number_of_rows) {
        ArrayList<CustomPair> startingPoints = new ArrayList<>();
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == currentChar) {
            	// If we have a matrix of length 1 then having a starting point means that there is a path
            	if (number_of_rows == 1) {foundPathsForLetter.add(currentChar);}
            	// Else (which is most cases) add it as a starting point
            	else {startingPoints.add(new CustomPair(0, j));}
            }
        }
        return startingPoints;
    }
}
