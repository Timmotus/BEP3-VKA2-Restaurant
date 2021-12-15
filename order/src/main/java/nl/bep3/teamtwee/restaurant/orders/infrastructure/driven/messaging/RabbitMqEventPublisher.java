package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.restaurant.orders.core.ports.messaging.OrderEventPublisher;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String restaurantExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.restaurantExchange = restaurantExchange;
    }

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}
