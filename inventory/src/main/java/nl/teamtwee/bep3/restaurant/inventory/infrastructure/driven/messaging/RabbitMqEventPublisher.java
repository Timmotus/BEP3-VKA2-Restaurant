package nl.teamtwee.bep3.restaurant.inventory.infrastructure.driven.messaging;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.inventory.core.domain.event.IngredientEvent;
import nl.teamtwee.bep3.restaurant.inventory.core.port.messaging.IngredientEventPublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqEventPublisher implements IngredientEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(IngredientEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}