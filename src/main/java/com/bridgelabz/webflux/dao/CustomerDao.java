package com.bridgelabz.webflux.dao;

import com.bridgelabz.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Processing Count : " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList())
                ;
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing Count in Stream Flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1, 20)
                .doOnNext(i -> System.out.println("Processing Count in Stream Flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }




}
