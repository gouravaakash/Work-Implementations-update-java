package org.example;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        StreamSources.intNumberStream().forEach(number-> System.out.println(number));

        StreamSources.intNumberStream().filter(number -> number<5)
                .forEach(number -> System.out.println(number));


        StreamSources.intNumberStream().filter(number -> number>5)
                .skip(1)
                .limit(2)
                .forEach(number -> System.out.println(number));

        Integer value = StreamSources.intNumberStream().filter(number -> number>5)
                .findFirst()
                .orElse(-1);
        System.out.println(value);

    // Print first name of all users in usersstream

        StreamSources.userStream()
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));

        //print first names in userStream for users that have id from member stream

        StreamSources.userStream()
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));

        StreamSources.userStream()
                .filter(u -> StreamSources.intNumberStream().anyMatch(i -> i  == u.getId()))
                .forEach(System.out::println);

        StreamSources.intNumberStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getId()==id))
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));

    }





}