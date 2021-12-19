package nl.bep3.teamtwee.restaurant.inventory.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class IngredientEvent {

    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public abstract String getEventKey();

}
