package com.example.OrderService_Ecommerce_Spring_boot.kafka;

import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaProducer {

    private final KafkaTemplate<String, OrderEventDTO> kafkaTemplate;
    private static final String TOPIC = "order-events";

    public OrderKafkaProducer(KafkaTemplate<String, OrderEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEventDTO eventDTO){
        kafkaTemplate.send(TOPIC, eventDTO);
    }
}