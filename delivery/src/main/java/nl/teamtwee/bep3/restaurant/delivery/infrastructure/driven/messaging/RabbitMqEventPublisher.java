package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driven.messaging;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;
import nl.teamtwee.bep3.restaurant.delivery.core.port.messaging.MenuEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements MenuEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String restaurantExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.restaurantExchange = restaurantExchange;
    }

    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}
