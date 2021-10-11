package com.rocketseat.expertsclub.checkoutproducer.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Json {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

    }


    public static String toJson(Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (IOException e) {
            log.error("Ocorreu um erro ao realizar o  parse do Json do object [{}] com as informações [{}]", content, e);
            throw new RuntimeException(e);
        }
    }
}
