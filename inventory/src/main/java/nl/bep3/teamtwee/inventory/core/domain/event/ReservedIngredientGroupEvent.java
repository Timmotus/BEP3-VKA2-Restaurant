package nl.bep3.teamtwee.inventory.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class ReservedIngredientGroupEvent {

    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public abstract String getEventKey();

}
