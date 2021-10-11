package com.rocketseat.expertsclub.checkoutproducer.service;

import com.rocketseat.expertsclub.checkoutproducer.configuration.AwsConfiguration;
import com.rocketseat.expertsclub.checkoutproducer.model.CheckoutMessage;
import com.rocketseat.expertsclub.checkoutproducer.sqs.SqsPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Random;
import java.util.UUID;

import static java.util.Collections.emptyMap;


@Service
@Slf4j
@RequiredArgsConstructor
public class CheckoutService {

    private final SqsPublisher sqsPublisher;
    private final AwsConfiguration awsConfiguration;
    private final OrderService orderService;


    public Mono<Void> sendToProcessOrderQueue() {
        return orderService.findOrderById(UUID.randomUUID().toString())
                .flatMap(validateOrder -> {
                    final CheckoutMessage checkoutMessage = CheckoutMessage.builder()
                            .orderId(UUID.randomUUID().toString())
                            .sellerId(UUID.randomUUID().toString())
                            .amount(new Random().nextLong())
                            .build();

                    return sqsPublisher.sendToProcessOrderQueue(checkoutMessage, emptyMap(), awsConfiguration.getProcessOrderQueueUrl());


                }).switchIfEmpty(Mono.defer(() -> {
                    log.warn("Não foi encontrada nenhuma ordem com essas informações");
                    return Mono.just(SendMessageResponse.builder().build());
                })).then();
    }

}
