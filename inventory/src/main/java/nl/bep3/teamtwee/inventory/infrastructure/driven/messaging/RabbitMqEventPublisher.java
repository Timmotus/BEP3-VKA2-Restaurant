package nl.bep3.teamtwee.inventory.infrastructure.driven.messaging;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.inventory.core.domain.event.IngredientEvent;
import nl.bep3.teamtwee.inventory.core.port.messaging.IngredientEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqEventPublisher implements IngredientEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(IngredientEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}