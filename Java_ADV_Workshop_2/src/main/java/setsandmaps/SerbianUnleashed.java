package setsandmaps;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class SerbianUnleashed {
    public static void main(String[] args) {
        LinkedHashMap<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while(!line.equals("End")) {
            if(!line.matches("(.+) @(.+) (\\d+) (\\d+)")) {
                line = scanner.nextLine();
                continue;
            }

            String venue = getVenue(line);
            String singer = getName(line);
            Integer profit = getProfit(line);

            if (data.containsKey(venue)) {
                LinkedHashMap<String, Integer> singerAndProfit = data.get(venue);
                if (singerAndProfit.containsKey(singer)) {
                    Integer currentProfit = singerAndProfit.get(singer);
                    singerAndProfit.put(singer, currentProfit + profit);
                } else {
                    singerAndProfit.put(singer, profit);
                }
            } else {
                LinkedHashMap<String, Integer> singerAndProfit = new LinkedHashMap<>();
                singerAndProfit.put(singer, profit);
                data.put(venue, singerAndProfit);
            }

            line = scanner.nextLine();
        }

        for (String venue : data.keySet()) {
            System.out.println(venue);

            data.get(venue).entrySet().stream()
                    .sorted((entr1, entr2) -> entr2.getValue().compareTo(entr1.getValue()))
                    .forEachOrdered(entry -> System.out.println("#  " + entry.getKey() + " -> " + entry.getValue()));
        }
    }

    public static String getName(String line) {
        return line.substring(0, line.indexOf('@')).trim();
    }

    public static String getVenue(String line) {
        int begin = line.indexOf('@') + 1;
        String venueAndTicket = line.substring(begin);
        String[] tokens = venueAndTicket.split(" ");

        return Arrays.stream(tokens)
                .limit(tokens.length - 2)
                .reduce((a, b) -> a + " " + b).get();
    }

    public static Integer getProfit(String line) {
        return Arrays.stream(line.split(" "))
                .filter(str -> str.matches("\\d+"))
                .map(Integer::parseInt)
                .reduce((a, b) -> a * b)
                .get();
    }
}
