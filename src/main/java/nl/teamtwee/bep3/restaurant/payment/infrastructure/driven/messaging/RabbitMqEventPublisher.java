package nl.teamtwee.bep3.restaurant.payment.infrastructure.driven.messaging;

import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.ports.messaging.PaymentEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements PaymentEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String paymentBoardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String paymentBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentBoardExchange = paymentBoardExchange;
    }

    public void publish(PaymentEvent event) {
        this.rabbitTemplate.convertAndSend(paymentBoardExchange, event.getEventKey(), event);
    }
}
