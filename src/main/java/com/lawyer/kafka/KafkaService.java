package com.lawyer.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    @Value("${kafka.topic.name}")
    private String kafkaTopic;

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public boolean priceUpdate(String price) {
        kafkaTemplate.send(kafkaTopic, price);
//        logger.info("message produced");
        logger.info("Sent message: {}", price);
        return true;
    }
}
