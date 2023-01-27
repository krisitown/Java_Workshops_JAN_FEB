package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class RadioactiveBunnies {
    static int playerRow = 0;
    static int playerCol = 0;
    static boolean playerAlive = true;
    static boolean gameOver = false;
    static boolean playerLeftTheMatrix = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int maxRows = dimensions[0];
        int maxCols = dimensions[1];

        char[][] matrix = new char[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            char[] row = scanner.nextLine().toCharArray();
            matrix[i] = row;
        }

        String commands = scanner.nextLine();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 'P') {
                    playerRow = i;
                    playerCol = j;
                }
            }
        }

        for (char direction : commands.toCharArray()) {
            movePlayer(matrix, direction);
            matrix = multiplyBunnies(matrix);

            if(gameOver) {
                break;
            }
        }

        if(matrix[playerRow][playerCol] == 'P' && playerLeftTheMatrix) {
            matrix[playerRow][playerCol] = '.';
        }

        for (int i = 0; i < maxRows; i++) {
            System.out.println(new String(matrix[i]));
        }

        if(playerAlive) {
            System.out.println("won: " + playerRow + " " + playerCol);
        } else {
            System.out.println("dead: " + playerRow + " " + playerCol);
        }

    }

    private static char[][] multiplyBunnies(char[][] matrix) {
        char[][] matrixCopy = new char[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrixCopy[row][col] = matrix[row][col];
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == 'B') {
                    spawnBunny(matrixCopy, row - 1, col);
                    spawnBunny(matrixCopy, row + 1, col);
                    spawnBunny(matrixCopy, row, col - 1);
                    spawnBunny(matrixCopy, row, col + 1);
                }
            }
        }

        return matrixCopy;
    }

    private static void spawnBunny(char[][] matrix, int row, int col) {
        if(inBounds(matrix, row, col)) {
            if(row == playerRow && col == playerCol && !gameOver) {
                gameOver = true;
                playerAlive = false;
            }
            matrix[row][col] = 'B';
        }
    }

    private static void movePlayer(char[][] matrix, char direction) {
        int oldPlayerRow = playerRow;
        int oldPlayerCol = playerCol;

        switch (direction) {
            case 'U':
                playerRow--;
                break;
            case 'D':
                playerRow++;
                break;
            case 'L':
                playerCol--;
                break;
            case 'R':
                playerCol++;
                break;
        }

        if(!inBounds(matrix, playerRow, playerCol)) {
            gameOver = true;
            playerRow = oldPlayerRow;
            playerCol = oldPlayerCol;
            playerLeftTheMatrix = true;
            return;
        }

        if(matrix[playerRow][playerCol] == 'B') {
            gameOver = true;
            playerAlive = false;
            return;
        }

        matrix[playerRow][playerCol] = 'P';
        matrix[oldPlayerRow][oldPlayerCol] = '.';
    }

    private static boolean inBounds(char[][] matrix, int row, int col) {
        return 0 <= row && row < matrix.length && 0 <= col && col < matrix[0].length;
    }
}