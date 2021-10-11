package com.rocketseat.expertsclub.checkoutproducer.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class OrderService {

    public Mono<String> findOrderById(String id) {
        return Mono.just(UUID.randomUUID().toString());


    }
}
