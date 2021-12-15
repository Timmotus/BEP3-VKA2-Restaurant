package nl.bep3.teamtwee.restaurant.orders.core.ports.messaging;

import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
