package org.example;

import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    private static final int APPEND = 1;
    private static final int ERASE = 2;
    private static final int INDEX = 3;
    private static final int UNDO = 4;

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        ArrayDeque<String> textStates = new ArrayDeque<>();

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            int command = Integer.parseInt(tokens[0]);

            if (command == APPEND) {
                textStates.push(text.toString());
                text.append(tokens[1]);
            } else if (command == ERASE) {
                textStates.push(text.toString());
                int count = Integer.parseInt(tokens[1]);
                text.delete(text.length() - count, text.length());
            } else if (command == INDEX) {
                int index = Integer.parseInt(tokens[1]);
                System.out.println(text.charAt(index - 1));
            } else if (command == UNDO) {
                text = new StringBuilder();
                text.append(textStates.pop());
            }
        }
    }
}
