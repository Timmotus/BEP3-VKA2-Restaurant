package nl.bep3_teamtwee.inventory.core.port.messaging;

import nl.bep3_teamtwee.inventory.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}
