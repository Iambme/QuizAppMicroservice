package com.quizapp.service.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        System.out.println("Sending message : " + message);
        kafkaTemplate.send("message",message);

    }
}
