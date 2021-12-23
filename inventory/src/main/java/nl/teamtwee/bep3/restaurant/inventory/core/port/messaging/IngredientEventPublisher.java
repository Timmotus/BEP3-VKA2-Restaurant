package nl.teamtwee.bep3.restaurant.inventory.core.port.messaging;

import nl.teamtwee.bep3.restaurant.inventory.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}