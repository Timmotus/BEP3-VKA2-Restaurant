package nl.bep3.teamtwee.kitchen.core.port.messaging;

import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}