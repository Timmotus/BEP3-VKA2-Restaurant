package nl.bep3.teamtwee.restaurant.inventory.core.port.messaging;

import nl.bep3.teamtwee.restaurant.inventory.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}
