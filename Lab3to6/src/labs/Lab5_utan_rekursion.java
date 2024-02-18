package labs;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lab5_utan_rekursion {

    private static TreeSet<Character> foundPathsForLetter = new TreeSet<>(); // Contains all letters with paths
    private static Set<CustomPair> visitedCoordinates = new HashSet<>();
    private static char currentChar;
    private static char[][] inputMatrix;
    private static char[] capitalLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out); // Reads an entire ROW of strings at a time

        while (io.hasMoreTokens()) {
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
                Set<CustomPair> visitedCoordinates = new HashSet<>(); // We reset it with every new character
                ArrayList<CustomPair> startingPoints = findStaringPoints(intMatrix); // We identify the coordinates of all
                                                                                       // starting points
                for (CustomPair startingPoint : startingPoints) {
                    pathfinder(startingPoint, intMatrix);
                }
            }

            // Lastly, we print our results
            if (foundPathsForLetter.isEmpty()) {
                System.out.println("0");
            } else {
                System.out.println(
                        foundPathsForLetter.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            }
        }
    }

    private static void pathfinder(CustomPair coordinates, int[][] intMatrix) {
        Queue<CustomPair> queue = new LinkedList<>();
        queue.add(coordinates);

        while (!queue.isEmpty()) {
            CustomPair currentCoordinates = queue.poll();
            int currentInt = intAtCoordinate(intMatrix, currentCoordinates);

            if (visitedCoordinates.contains(currentCoordinates) || currentInt == 0) {
                continue;
            } else {
                if (currentInt == 2) {
                    foundPathsForLetter.add(currentChar);
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
            queue.add(coordinates);
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
