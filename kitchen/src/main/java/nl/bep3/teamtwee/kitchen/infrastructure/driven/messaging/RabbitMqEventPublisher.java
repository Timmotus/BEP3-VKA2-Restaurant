package nl.bep3.teamtwee.kitchen.infrastructure.driven.messaging;

import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.kitchen.core.port.messaging.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}
