package nl.bep3.teamtwee.restaurant.inventory.infrastructure.driven.messaging;

import nl.bep3.teamtwee.restaurant.inventory.core.domain.event.IngredientEvent;
import nl.bep3.teamtwee.restaurant.inventory.core.port.messaging.IngredientEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements IngredientEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(IngredientEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}
