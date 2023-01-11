package org.example;

public class MultidimensionalArrays {
    public static void main(String[] args) {
        char[][] charArr = new char[2][];
        char[] row1 = new char[] {'a', 'b'};
        charArr[0] = row1;

        char[] row2 = new char[] {'a', 'b', 'c'};
        charArr[1] = row2;

        //jagged array
        // a b
        // a b c

        // matrix
        // a b
        // b a
        // c a
        // OR
        // a b c
        // b c a
        // OR
        // a b
        // b a

        for (int row = 0; row < charArr.length; row++) {
            for (int col = 0; col < charArr[row].length; col++) {
                System.out.print(charArr[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println("========================");

        Object[][] matrix = new Object[2][];
        Object[] strRow = new Object[] {"asd", "zxc"};
        Object[] intRow = new Object[] {1, 2, 3};
        matrix[0] = strRow;
        matrix[1] = intRow;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
