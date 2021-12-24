package nl.teamtwee.bep3.restaurant.payment.infrastructure.driven.messaging;

import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.ports.messaging.PaymentEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RabbitMqEventPublisher implements PaymentEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public void publishSend(PaymentEvent event) {
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}
