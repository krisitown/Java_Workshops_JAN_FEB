package functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.IntFunction;

public class CustomCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Arrays.sort(numbers, (a, b) -> {
            if (a % 2 == b % 2) {
                return a.compareTo(b);
            }

            if (a % 2 == 0) {
                return -1;
            }

            return 1;
        });

        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
    }
}
