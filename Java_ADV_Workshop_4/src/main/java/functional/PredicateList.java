package functional;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Integer> divisors = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).toList();

        Stream.iterate(1, i -> i + 1)
                .limit(n)
                .filter(num -> divisors.stream().allMatch(divisor -> num % divisor == 0))
                .map(num -> num.toString())
                .reduce((num1, num2) -> num1 + " " + num2)
                .ifPresent(System.out::println);
    }
}
