package nl.bep3_teamtwee.inventory_service.infrastructure.driven.messaging;

import nl.bep3_teamtwee.inventory_service.core.domain.event.ItemEvent;
import nl.bep3_teamtwee.inventory_service.core.port.messaging.ItemEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements ItemEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(ItemEvent event) {
        this.rabbitTemplate.convertAndSend("", event.getEventKey(), event);
    }
}
