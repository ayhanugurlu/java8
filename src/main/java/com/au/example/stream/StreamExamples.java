package com.au.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {


    public static void main(String[] args) {
        optinal();
        stream();
    }

    public static void stream() {

        System.out.println("-------------------------------- sort limit-------------------------------------------");
        Stream.of("m", "k", "c", "t").sorted().limit(3).forEach(s1 -> System.out.println(s1));
        System.out.println("--------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- find first-------------------------------------------");
        System.out.println(Stream.of("m", "k", "c", "t").sorted().limit(3).findFirst());
        System.out.println("--------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- find Any-------------------------------------------");
        System.out.println(Stream.of("m", "k", "c", "t").parallel().sorted().limit(3).findAny());
        System.out.println("--------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- filter-------------------------------------------");
        List<String> words = Arrays.asList("hello", null, "");
        words.stream().filter(t -> t != null) // ["hello", ""]
                .filter(t -> !t.isEmpty()) // ["hello"]
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- any match-------------------------------------------");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7);
        System.out.println(stream.anyMatch(i -> i % 3 == 0)); // true
        System.out.println("--------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- allMatch -------------------------------------------");
        IntStream stream1 = IntStream.of(1, 2, 3, 4, 5, 6, 7);
        System.out.println(stream1.allMatch(i -> i > 0)); // true
        System.out.println("--------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- noneMatch -------------------------------------------");
        IntStream stream2 = IntStream.of(1, 2, 3, 4, 5, 6, 7);
        System.out.println(stream2.allMatch(i -> i > 0)); // true
        System.out.println("--------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- sorted -------------------------------------------");
        List<String> strings = Arrays.asList("Stream", "Operations", "on", "Collections");
        strings.stream().sorted((s1, s2) -> s2.length() - s1.length()).forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- count -------------------------------------------");

        List<Integer> list = Arrays.asList(57, 38, 37, 54, 2);
        System.out.println(list.stream().count()); // 5
        System.out.println("------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- sum -------------------------------------------");

        IntStream intStream = list.stream().mapToInt(Integer::intValue);
        System.out.println(intStream.sum()); // 5
        System.out.println("------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- example -------------------------------------------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));


        Stream.of("d2", "a2", "b1", "b3", "c")
                .parallel()
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .parallel()
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .sorted()
                .forEach(s -> System.out.println("forEach: " + s));


        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);  // a1


        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);


        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });


        //Reusing Streams
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
        System.out.println("------------------------------------------------------------------------------------");
    }


    public static void optinal() {

        System.out.println("-------------------------------- empty -------------------------------------------");
        Optional<String> empty = Optional.empty();
        System.out.println(empty);
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- of -------------------------------------------");
        String name = "ayhan";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt);
        System.out.println("------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- ofNullable -------------------------------------------");
        name = "ayhan";
        Optional<String> optName = Optional.ofNullable(name);
        System.out.println(optName);

        name = null;
        optName = Optional.ofNullable(name);
        System.out.println(optName);
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- isPresent -------------------------------------------");
        name = "ayhan";
        optName = Optional.ofNullable(name);
        System.out.println(optName.isPresent());

        name = null;
        optName = Optional.ofNullable(name);
        System.out.println(optName.isPresent());
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- ifPresent -------------------------------------------");
        name = "ayhan";
        optName = Optional.ofNullable(name);
        optName.ifPresent(s -> System.out.println(s));

        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- orElse -------------------------------------------");
        name = "ayhan";
        name = Optional.ofNullable(name).orElse("Mete");
        System.out.println(name);
        name = null;
        name = Optional.ofNullable(name).orElse("Mete");
        System.out.println(name);
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- orElseGet -------------------------------------------");
        name = "ayhan";
        name = Optional.ofNullable(name).orElseGet(() -> {
            return "Mete";
        });
        System.out.println(name);
        name = null;
        name = Optional.ofNullable(name).orElseGet(() -> {
            return "Mete";
        });
        System.out.println(name);
        System.out.println("------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- orElseThrow -------------------------------------------");
        try {
            name = "ayhan";
            name = Optional.ofNullable(name).orElseThrow(() -> {
                throw new IllegalArgumentException("illegal");
            });
            System.out.println(name);
        } catch (IllegalArgumentException e) {
            System.out.println("examption");
        }
        try {
            name = null;
            name = Optional.ofNullable(name).orElseThrow(() -> {
                throw new IllegalArgumentException("illegal");
            });
            System.out.println(name);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception");
        }
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- get ------------------------------------------------");
        optName = Optional.of("ayhan");
        System.out.println(optName.get());
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------------------------- filter ------------------------------------------------");
        optName = Optional.of("ayhan");
        System.out.println(optName.filter(s -> s.equals("ayhan")).isPresent());
        System.out.println(optName.filter(s -> s.equals("amete")).isPresent());
        System.out.println("------------------------------------------------------------------------------------");


        System.out.println("-------------------------------- map ------------------------------------------------");
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        listOptional.map(strings ->
                {
                    return strings.stream().map(s -> {
                        return s.toUpperCase();
                    });
                }
        ).orElse(Arrays.asList("ayhan").stream()).forEach(s -> {
            System.out.println(s);
        });


        System.out.println("------------------------------------------------------------------------------------");

    }
}
