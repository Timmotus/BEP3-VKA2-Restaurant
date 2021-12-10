package nl.bep3_teamtwee.inventory_service.core.port.messaging;

import nl.bep3_teamtwee.inventory_service.core.domain.event.ItemEvent;

public interface ItemEventPublisher {
    void publish(ItemEvent event);
}
