package nl.teamtwee.bep3.restaurant.kitchen.core.port.messaging;

import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}