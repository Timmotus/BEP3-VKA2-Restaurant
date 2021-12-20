package nl.teamtwee.bep3.restaurant.payment.core.ports.messaging;

import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;

public interface PaymentEventPublisher {
    void publish(PaymentEvent event);
}
