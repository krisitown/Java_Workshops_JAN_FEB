package org.example;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Main {
    private static BiPredicate<String, String> STARTS_WITH =  (str, prefix) -> str.startsWith(prefix);
    private static BiPredicate<String, String> CONTAINS =  (str, substring) -> str.contains(substring);
    private static BiPredicate<String, String> LENGTH =  (str, length) -> str.length() == Integer.parseInt(length);

    private static Map<String, BiPredicate<String, String>> functionMap = Map.of(
            "StartsWith", STARTS_WITH,
            "Contains", CONTAINS,
            "Length", LENGTH
    );

    public static void main(String[] args) {
        Set<String> filters = new HashSet<>();
        Map<String, List<String>> specifications = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] carParking = new String[rows][cols];

        String command = scanner.nextLine();

        while(!command.equals("End")) {
            String[] tokens = command.split("\\s+");

            if(tokens[0].equals("AddCar")) {
                int row = Integer.parseInt(tokens[1]);
                int col = Integer.parseInt(tokens[2]);
                if (carParking[row][col] != null) {
                    System.out.printf("Unable to park car on (%d, %d)!\n", row, col);
                } else {
                    List<String> specs = Arrays.stream(Arrays.copyOfRange(tokens, 4, tokens.length)).toList();
                    specifications.put(tokens[3], specs);
                    carParking[row][col] = tokens[3];
                }
            } else if (tokens[0].equals("MoveCar")) {
                int row = Integer.parseInt(tokens[2]);
                int col = Integer.parseInt(tokens[3]);
                int[] currentCoordinates = findCarByModel(tokens[1], carParking);

                String currentModel = carParking[row][col];
                carParking[row][col] = tokens[1];
                carParking[currentCoordinates[0]][currentCoordinates[1]] = currentModel;
            } else if (tokens[0].equals("AddFilter")) {
                filters.add(tokens[1] + ";" + tokens[2]);
            } else if (tokens[0].equals("RemoveFilter")) {
                filters.remove(tokens[1] + ";" + tokens[2]);
            }

            command = scanner.nextLine();
        }

        for (int row = 0; row < carParking.length; row++) {
            for (int col = 0; col < carParking[row].length; col++) {
                String spot = carParking[row][col];
                if (spot == null) {
                    spot = "Empty";
                }
                System.out.print(spot + " ");
            }
            System.out.println();
        }

        for (String filter : filters) {
            specifications = applyFilter(specifications, filter);
        }

        specifications.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue().stream()
                        .sorted(Comparator.reverseOrder())
                        .reduce((spec1, spec2) -> spec1 + ", " + spec2)
                        .get())
                .reduce((str1, str2) -> str1 + "\n" + str2)
                .ifPresent(System.out::println);
    }

    private static int[] findCarByModel(String model, String[][] carPark) {
        for (int i = 0; i < carPark.length; i++) {
            for (int j = 0; j < carPark[i].length; j++) {
                if(model.equals(carPark[i][j])) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    private static Map<String, List<String>> applyFilter(Map<String, List<String>> data, String filter) {
        String[] tokens = filter.split(";");
        return data.entrySet().stream().filter(entry ->
                entry.getValue().stream()
                .anyMatch(str -> functionMap.get(tokens[0]).test(str, tokens[1])))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}