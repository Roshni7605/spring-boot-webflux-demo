package com.bridgelabz.webflux.handler;

import com.bridgelabz.webflux.dao.CustomerDao;
import com.bridgelabz.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    /**
     * If we'll return stream rather than the object then the publisher will keep on emitting
     * the event to the subscriber.
     * We need to send stream instead of an object
     *
     * @param request
     * @return
     */

    public Mono<ServerResponse> getCustomersStream(ServerRequest request) {
        Flux<Customer> customerList = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerList, Customer.class);
    }


}
