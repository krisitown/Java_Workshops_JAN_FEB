package functional;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class PartyFilteringModule {
    private static final BiPredicate<String, String> STARTS_WITH = (name, prefix) -> name.startsWith(prefix);
    private static final BiPredicate<String, String> ENDS_WITH = (name, suffix) -> name.endsWith(suffix);
    private static final BiPredicate<String, String> LENGTH = (name, length) -> name.length() == Integer.parseInt(length);
    private static final BiPredicate<String, String> CONTAINS = (name, substring) -> name.contains(substring);
    private static final Map<String, BiPredicate<String, String>> filterMap = Map.of(
            "Starts with", STARTS_WITH,
            "Ends with", ENDS_WITH,
            "Length", LENGTH,
            "Contains", CONTAINS
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stream<String> participants =  Arrays.stream(scanner.nextLine().split("\\s+"));

        String line = scanner.nextLine();
        List<Map.Entry<BiPredicate<String, String>, String>> filters = new ArrayList<>();
        while(!line.equals("Print")) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String filterName = tokens[1];
            String parameter = tokens[2];

            var filter = filterMap.get(filterName);
            if (command.equals("Add filter")) {
                filters.add(entry(filter, parameter));
            } else {
                filters.remove(entry(filter, parameter));
            }

            line = scanner.nextLine();
        }

        for (Map.Entry<BiPredicate<String, String>, String> element : filters) {
            participants = participants.filter(name -> !element.getKey().test(name, element.getValue()));
        }

        System.out.println(String.join(" ", participants.collect(Collectors.toList())));
    }
}
