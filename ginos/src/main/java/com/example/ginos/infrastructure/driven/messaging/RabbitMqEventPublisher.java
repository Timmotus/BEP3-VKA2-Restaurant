package com.example.ginos.infrastructure.driven.messaging;

import com.example.ginos.core.domain.event.MenuEvent;
import com.example.ginos.core.port.messaging.MenuEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements MenuEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(MenuEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}
