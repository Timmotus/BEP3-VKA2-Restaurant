package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.messaging;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderEvent;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.messaging.OrderEventPublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}