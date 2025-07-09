package com.lawyer.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaService kafkaService;

    @GetMapping
    public ResponseEntity<?> kafka() {
//        int price = 1 + (int)(Math.random() * 100); // price between 1-100
        boolean result = kafkaService.priceUpdate("price");
        return new ResponseEntity<>(Map.of("message", "Price of the bill generated"), HttpStatus.OK);

    }
}
