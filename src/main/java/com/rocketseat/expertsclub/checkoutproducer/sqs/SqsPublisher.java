package com.rocketseat.expertsclub.checkoutproducer.sqs;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Map;

import static com.rocketseat.expertsclub.checkoutproducer.utils.Json.toJson;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsPublisher {

    private final SqsAsyncClient sqsAsyncClient;

    public <T> Mono<SendMessageResponse> sendToProcessOrderQueue(final T object,
                                                                 final Map<String, MessageAttributeValue> messageAttributes,
                                                                 final String queue) {
        final String messageBody = toJson(object);
        return sendToOrderProcessQueue(messageBody, messageAttributes, queue);
    }


    public Mono<SendMessageResponse> sendToOrderProcessQueue(final String message,
                                                             final Map<String, MessageAttributeValue> messageAttributes,
                                                             final String queue) {
        log.info("Enviando mensagem com a ordem para a fila [{}] sqs , com o conteudo: [{}]", queue, message);

        final SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queue)
                .messageAttributes(messageAttributes)
                .messageBody(message)
                .build();

        return Mono.fromFuture(sqsAsyncClient.sendMessage(request))
                .doOnSuccess(result -> log.info("Mensagem enviada para a fila de processamento de ordens [{}] com sucesso, resposta: [{}]", queue, result))
                .doOnError(error -> log.error("Ocorreu um erro ao enviar a mensagem para a fila de processamento, mensagem que seria enviada: [{}], Erro retornado: [{}]", message, error));
    }


}
