package com.example.rabbitMQ.service;

import com.example.rabbitMQ.configuration.RabbitMQConfig;
import com.example.rabbitMQ.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(final User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, user);
    }
}
