package org.example;

import java.util.stream.Stream;

public class StreamSources {

    public static Stream<String> stringNumberStream(){
        return Stream.of("one", "two", "three", "four", "five", "six", "seven");
    }

    public static Stream<Integer> intNumberStream(){

        return Stream.iterate(1, i -> i * 2).limit(10);
    }

    public static Stream<User> userStream(){
        return Stream.of(
                new User(1, "Lionel", "Messi"),
                new User(2, "Cristiano", "Ronaldo"),
                new User(3, "Diego", "Maradona"),
                new User(4, "Zinedine", "Zidane"),
                new User(5, "Jurgen", "Klinsmann"),
                new User(6, "Gareth", "Bale")
        );
    }
}
