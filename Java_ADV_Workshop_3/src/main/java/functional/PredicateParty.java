package functional;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateParty {
    private static final Map<String, BiPredicate<String, String>> predicateMap = Map.of(
                "StartsWith", String::startsWith,
                "EndsWith", String::endsWith,
            "Length", (name, length) -> name.length() == Integer.parseInt(length)
            );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> participants = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String line = scanner.nextLine();

        while(!line.equals("Party!")) {
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String filterName = tokens[1];
            String parameter = tokens[2];

            var filter = predicateMap.get(filterName);

            if (command.equals("Remove")) {
                participants = participants.stream().filter(el -> !filter.test(el, parameter)).collect(Collectors.toList());
            } else {
                List<String> matchingParticipants = participants.stream()
                        .filter(el -> filter.test(el, parameter)).collect(Collectors.toList());

                List<String> temp = new ArrayList<>();
                temp.addAll(participants);
                temp.addAll(matchingParticipants);

                participants = temp;
            }

            line = scanner.nextLine();
        }

        String result = participants.stream()
                .sorted()
                .reduce((str1, str2) -> str1 + ", " + str2)
                .map(str -> str + " are going to the party!")
                .orElse("Nobody is going to the party!");

        System.out.println(result);
    }
}
