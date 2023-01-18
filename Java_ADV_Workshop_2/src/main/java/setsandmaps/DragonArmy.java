package setsandmaps;

import java.util.*;

public class DragonArmy {
    private static final int DEFAULT_DAMAGE = 45;
    private static final int DEFAULT_HEALTH = 250;
    private static final int DEFAULT_ARMOR = 10;

    public static void main(String[] args) {
        LinkedHashMap<String, TreeMap<String, List<Integer>>> data = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            String[] tokens = line.split("\\s+");
            String type = tokens[0];
            String name = tokens[1];

            Integer damage = getStat(tokens[2], DEFAULT_DAMAGE);
            Integer health = getStat(tokens[3], DEFAULT_HEALTH);
            Integer armor = getStat(tokens[4], DEFAULT_ARMOR);

            if (data.containsKey(type)) {
                TreeMap<String, List<Integer>> dragonType = data.get(type);
                dragonType.put(name, getStats(damage, health, armor));
            } else {
                List<Integer> stats = getStats(damage, health, armor);
                TreeMap<String, List<Integer>> dragon = new TreeMap<>();
                dragon.put(name, stats);
                data.put(type, dragon);
            }
        }

        for (String type : data.keySet()) {
            TreeMap<String, List<Integer>> dragons = data.get(type);
            double[] averages = dragons.values().stream().reduce(List.of(0, 0, 0), (list1, list2) -> {
                List<Integer> sums = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    sums.add(list1.get(i) + list2.get(i));
                }
                return sums;
            }).stream().mapToDouble(stat -> stat/ (double)dragons.size()).toArray();

            System.out.printf(String.format("%s::(%.2f/%.2f/%.2f)\n", type, averages[0], averages[1], averages[2]));

            for (String name : dragons.keySet()) {
                List<Integer> stats = dragons.get(name);
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d\n", name, stats.get(0),
                         stats.get(1), stats.get(2));
            }
        }
    }

    private static List<Integer> getStats(Integer damage, Integer health, Integer armor) {
        List<Integer> stats = new ArrayList<>();
        stats.add(damage);
        stats.add(health);
        stats.add(armor);
        return stats;
    }

    private static Integer getStat(String stat, Integer defaultStat) {
        if (stat.equals("null")) {
            return defaultStat;
        }

        return Integer.parseInt(stat);
    }
}
