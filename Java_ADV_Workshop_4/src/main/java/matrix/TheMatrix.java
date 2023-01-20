package matrix;

import java.util.Arrays;
import java.util.Scanner;


public class TheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int maxRow = dimensions[0];
        int maxCol = dimensions[1];
        
        char[][] matrix = new char[maxRow][maxCol];

        for (int i = 0; i < maxRow; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            char[] row = new char[maxCol];

            for (int j = 0; j < maxCol; j++) {
                row[j] = tokens[j].charAt(0);
            }

            matrix[i] = row;
        }

        char symbol = scanner.nextLine().charAt(0);
        int[] startingDimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int startRow = startingDimensions[0];
        int startCol = startingDimensions[1];
        char startChar = matrix[startRow][startCol];

        //--------------------------------------

        matrix = fillAtCoordinates(matrix, startRow, startCol, symbol, startChar);

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static char[][] fillAtCoordinates(char[][] matrix, int row, int col, char fillChar, char startChar) {
        matrix[row][col] = fillChar;

        matrix = checkNeighbour(matrix, col, fillChar, startChar, row + 1);
        matrix = checkNeighbour(matrix, col + 1, fillChar, startChar, row);
        matrix = checkNeighbour(matrix, col, fillChar, startChar, row - 1);
        matrix = checkNeighbour(matrix, col - 1, fillChar, startChar, row);

        return matrix;
    }

    private static char[][] checkNeighbour(char[][] matrix, int col, char fillChar, char startChar, int down) {
        if(isInBounds(matrix, down, col)) {
            char currentChar = matrix[down][col];
            if (currentChar == startChar) {
                matrix = fillAtCoordinates(matrix, down, col, fillChar, startChar);
            }
        }
        return matrix;
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }
}
