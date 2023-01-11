package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class ParkingSystem {
    private static final char ENTRANCE = 'e';
    private static final char FREE_SPACE = ' ';
    private static final char TAKEN_SPACE = 'f';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        char[][] parkingLot = new char[dimensions[0]][dimensions[1]];
        for (int row = 0; row < parkingLot.length; row++) {
            for (int col = 0; col < parkingLot[row].length; col++) {
                if (col == 0) {
                    parkingLot[row][col] = ENTRANCE;
                } else {
                    parkingLot[row][col] = FREE_SPACE;
                }
            }
        }

        String line = scanner.nextLine();
        while(!line.equals("stop")) {
            int[] arguments = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int row = arguments[1];
            int col = arguments[2];
            if (parkingLot[row][col] == FREE_SPACE) {
                int distanceTraveled = getDistance(arguments[0], arguments[1], arguments[2]);
                System.out.println(distanceTraveled);
                parkingLot[row][col] = TAKEN_SPACE;
            } else {
                int count = 0;
                boolean foundSpace = false;
                while (col - count > 0 || col + count < parkingLot[row].length) {
                    if (col - count > 0 && parkingLot[row][col - count] == FREE_SPACE) {
                        int distance = getDistance(arguments[0], row, col - count);
                        System.out.println(distance);
                        parkingLot[row][col - count] = TAKEN_SPACE;
                        foundSpace = true;
                    } else if (col + count < parkingLot[row].length && parkingLot[row][col + count] == FREE_SPACE) {
                        int distance = getDistance(arguments[0], row, col + count);
                        System.out.println(distance);
                        parkingLot[row][col + count] = TAKEN_SPACE;
                        foundSpace = true;
                    }
                    count++;
                }

                if (!foundSpace) {
                    System.out.println("Row " + row + " full");
                }
            }

            line = scanner.nextLine();
        }
    }

    private static int getDistance(int entryRow, int targetRow, int targetCol) {
        return Math.abs(entryRow - targetRow) + targetCol + 1;
    }
}