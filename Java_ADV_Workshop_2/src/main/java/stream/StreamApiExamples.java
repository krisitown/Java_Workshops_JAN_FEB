package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiExamples {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 5, 7, 13};

        IntStream stream = Arrays.stream(arr);
        stream = stream
                .map(a -> {
                    System.out.println("Calling MAP");
                    return a + 1;
                })
                .filter(a -> {
                    System.out.println("Calling filter");
                    return a % 3 == 0;
                });

        Scanner scanner  = new Scanner(System.in);
        scanner.nextLine();

        // 1, 2, 3, 4
        // 3, 3, 4
        // 6, 4
        // 10

        int sum = stream.reduce((a, b) -> {
            System.out.println("Calling REDUCE");
            return a * b;
        }).getAsInt();
        System.out.println(sum);

        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2);

        List<String> arr3 = infiniteStream
                .map(a -> a + 1)
                .map(a -> a.toString())
                .limit(100)
                .collect(Collectors.toList());

        String[] strArr = new String[] {"asd", "fds", "zxc"};

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(3);
        list2.add(4);

        list.add(list1);
        list.add(list2);

        List<String> flatList = list.stream()
                .flatMap(l -> l.stream().map(Object::toString))
                .collect(Collectors.toList());

        System.out.println(String.join(", ", flatList));


        //Optionals in Java
        Optional<Integer> optional = Optional.of(10);
        Integer result = optional.orElse(-1);
        System.out.println(result);

        Optional<Integer> optional2 = Optional.empty();
        Integer result2 = optional2.orElse(-1);
        System.out.println(result2);

        optional.ifPresent(v -> System.out.println(v));
        optional2.ifPresent(v -> System.out.println(v));

        String str = null;
        if (str == null) {
            //todo: handle
        }

        str.split("\\s+");

        Optional<String> strOptional = Optional.empty();
        strOptional.map(value -> value.split("\\s+"));

        List<String> strList1 = new ArrayList<>();
        ArrayList<String> strList2 = new ArrayList<>();

        join1(", ", strList1);
        join1(", ", strList2);

//        join2(", ", strList1); doesn't work
//        join2(", ", strList2); doesn't work

        //IntStream -> int[] (mapToInt)
        //Stream<Integer> -> Integer[] / List<Integer> (map)

        int a = 10;
        Integer b = a; //auto boxing
        int c = b; //unboxing
    }

    public static String join1(String delimiter, List list) {
        return String.join(delimiter, list);
    }

    public static String join2(String delimiter, LinkedList list) {
        return String.join(delimiter, list);
    }

    public static <T> T getFirst(List<T> list) {
        return list.get(0);
    }

    public static <T> Optional<T> findObject(List<T> list, T object) {
        for (T element : list) {
            if (element == object) {
                return Optional.of(element);
            }
        }

        return Optional.empty();
    }
}
