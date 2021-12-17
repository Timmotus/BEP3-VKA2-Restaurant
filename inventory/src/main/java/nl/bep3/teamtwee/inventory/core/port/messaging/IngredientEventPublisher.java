package nl.bep3.teamtwee.inventory.core.port.messaging;

import nl.bep3.teamtwee.inventory.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}
