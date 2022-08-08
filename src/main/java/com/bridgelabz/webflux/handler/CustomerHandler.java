package com.bridgelabz.webflux.handler;

import com.bridgelabz.webflux.dao.CustomerDao;
import com.bridgelabz.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    /**
     * ServerResponse : To map all the response metadata
     * ServerRequest : To get all the request metadata
     *
     * @return Server response that will load the customer data
     */
    public Mono<ServerResponse> loadCustomersData(ServerRequest request) {
        Flux<Customer> customerList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    /**
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getCustomersList().filter(customer -> customer.getId() == customerId)
                                                 .next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    /**
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse.ok().body(saveResponse, Customer.class);
    }
}
