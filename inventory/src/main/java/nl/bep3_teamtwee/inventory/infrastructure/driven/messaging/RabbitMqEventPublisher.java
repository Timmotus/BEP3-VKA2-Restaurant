package nl.bep3_teamtwee.inventory.infrastructure.driven.messaging;

import nl.bep3_teamtwee.inventory.core.domain.event.IngredientEvent;
import nl.bep3_teamtwee.inventory.core.port.messaging.IngredientEventPublisher;
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
