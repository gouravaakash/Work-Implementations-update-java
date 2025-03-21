package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactiveSources {

    public static Flux<String> stringNumbersFlux(){
        return Flux.just("one","two","three","four","five","six","seven","eight","nine")
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumberFlux(){
        return Flux
                .range(1,10)
                .delayElements(Duration.ofSeconds(1));
    }
    public static Flux<Integer> intNumbersFluxWithException(){
        return Flux
                .range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .map(e -> {
                    if(e==5) throw new RuntimeException("An error happend in flux");
                    return 0;
                });

    }
    public static Mono<Integer> intNumberMono(){
        return Mono.just(42)
                .delayElement(Duration.ofSeconds(1));
    }

    public static Flux<User> userFlux(){
        return Flux.just(
                new User(1, "Lionel", "Messi"),
                        new User(2, "Cristiano", "Ronaldo"),
                        new User(3, "Diego", "Maradona"),
                        new User(4, "Zinedine", "Zidane"),
                        new User(5, "Jurgen", "Klinsmann"),
                        new User(6, "Gareth", "Bale")
        );
    }


}