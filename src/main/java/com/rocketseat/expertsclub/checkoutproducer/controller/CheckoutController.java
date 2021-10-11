package com.rocketseat.expertsclub.checkoutproducer.controller;


import com.rocketseat.expertsclub.checkoutproducer.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {


    private final CheckoutService checkoutService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/create-payment")
    public Mono<Void> createPayment() {
        log.info("Enviando ordem para a fila de processamento");
        return checkoutService.sendToProcessOrderQueue()
                .doOnError(error -> log.info("Ocorreu um erro ao enviar a mensagem com a ordem para a fila ", error));


    }


}
