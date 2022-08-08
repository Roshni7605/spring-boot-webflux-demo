package com.bridgelabz.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        /**
         * Mono can handle only 1 element (mono object)
         * Mono & Flux acts as a publisher
         * To access any publisher first subscriber needs to call subscribe method
         */
//        Mono<String> stringMono = Mono.just("Hello Bridgelabz").log();

        Mono<?> stringMono = Mono.just("Hello Bridgelabz")
                                 .then(Mono.error(new RuntimeException("Exception Occurred")))
                                 .log();
        // publisher start emitting the event
        stringMono.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));

        /**
         *  12:02:52.882 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
         * 12:02:52.896 [main] INFO reactor.Mono.Just.1 - | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
         * 12:02:52.898 [main] INFO reactor.Mono.Just.1 - | request(unbounded)
         * 12:02:52.899 [main] INFO reactor.Mono.Just.1 - | onNext(Hello Bridgelabz)
         * Hello Bridgelabz
         * 12:02:52.899 [main] INFO reactor.Mono.Just.1 - | onComplete()
         */
    }

    @Test
    public void testFlux(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Hibernate")
                                      .concatWithValues("AWS")
                                      .concatWith(Flux.error(new RuntimeException("Exception Occurred in Flux...")))
                                      .log();
        stringFlux.subscribe(System.out::println);

        /**
         * 12:25:12.731 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
         * 12:25:12.747 [main] INFO reactor.Flux.ConcatArray.1 - onSubscribe(FluxConcatArray.ConcatArraySubscriber)
         * 12:25:12.757 [main] INFO reactor.Flux.ConcatArray.1 - request(unbounded)
         * 12:25:12.758 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Spring)
         * Spring
         * 12:25:12.758 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Spring Boot)
         * Spring Boot
         * 12:25:12.758 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Hibernate)
         * Hibernate
         * 12:25:12.759 [main] INFO reactor.Flux.ConcatArray.1 - onNext(AWS)
         * AWS
         * 12:25:12.759 [main] INFO reactor.Flux.ConcatArray.1 - onComplete()
         */
    }
}
