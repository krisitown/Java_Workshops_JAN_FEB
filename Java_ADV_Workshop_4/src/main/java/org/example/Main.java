package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Cat> catList = new ArrayList<>();
        while(!line.equals("End")) {
            String[] tokens = line.split("\\s+");

            Cat cat = null;
            switch (tokens[0]) {
                case "Siamese":
                    cat = new Siamese(tokens[1], Double.parseDouble(tokens[2]));
                    break;
                case "Cymric":
                    cat = new Cymric(tokens[1], Double.parseDouble(tokens[2]));
                    break;
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(tokens[1], Double.parseDouble(tokens[2]));
                    break;
                default:
                    throw new IllegalArgumentException("No such cat type exists!");
            }

            catList.add(cat);

            line = scanner.nextLine();
        }

        String nameOfCat = scanner.nextLine();

        catList.stream()
                .filter(cat -> cat.getName().equals(nameOfCat))
                .findFirst()
                .ifPresent(System.out::println);
    }
}