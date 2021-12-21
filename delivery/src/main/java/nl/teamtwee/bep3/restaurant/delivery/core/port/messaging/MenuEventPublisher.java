package nl.teamtwee.bep3.restaurant.delivery.core.port.messaging;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;

public interface MenuEventPublisher {
    void publish(DeliveryEvent event);
}
