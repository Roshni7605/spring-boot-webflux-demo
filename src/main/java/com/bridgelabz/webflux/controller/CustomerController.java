package com.bridgelabz.webflux.controller;

import com.bridgelabz.webflux.dto.Customer;
import com.bridgelabz.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.loadAllCustomers();
    }

    /**
     * Feature: Asynchronous & Non-blocking
     * In reactive programming mono & flux work as a datatype
     * Here, we send data as event stream rather than sending as an entire object
     * In traditional approach if we'll cancel the request, still the backend is processing the data
     * But in reactive programming, if we'll cancel the request. Then there is no request flow.
     *
     * @return list of customers
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream(){
        return service.loadAllCustomersStream();
    }

}
