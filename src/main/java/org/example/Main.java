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







    }





}