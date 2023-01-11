package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        byte b = 1;
        short s = 11000;
        int i = 1;
        long l = 123123123;
        char c = 'a';
        String str = "Hello world!";
        byte[] byteArr = new byte[] {1, 2, 3, 4, 5};
        str.toCharArray();
        new String(new char[] {'h', 'e'});
        boolean condition = true;
        boolean condition2 = false;


        Integer iObject = i;

        f(iObject);
        add(1, 2);

        if (condition) {
            System.out.println("Condition is true");
        } else if (condition2) {
            System.out.println("Condition2 is true, but condition1 is false");
        } else {
            System.out.println("All conditions are false");
        }

        int count = 0;
        while (count < 10) {
            System.out.println(count);
            count++;
        }

        do {
            System.out.println(count);
        } while (count < 9);

        System.out.println("========");
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
        }

        InputStream inputStream = new FileInputStream("/Users/kristiyanstoyanov/Projects/SoftUni/DXC_2/test.txt");
        BufferedReader reader = new BufferedReader(new FileReader("/Users/kristiyanstoyanov/Projects/SoftUni/DXC_2/test.txt"));
        byte[] result = inputStream.readAllBytes();

        System.out.println(new String(result));
        System.out.println(reader.readLine());

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());

        String input = "1 3  4   5 1        17"; //1, 3, 4, 5, 1, 17
        String[] numbers = input.split("\\s+");
        System.out.println(String.join(", ", numbers));
    }

    public static void f(Integer i) {
        i += 1;
    }

    private static int add(int a, int b) {
        return a + b;
    }
}