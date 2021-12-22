package nl.bep3.teamtwee.restaurant.orders.core.domain.event;

import java.time.Instant;
import java.util.UUID;

public abstract class OrderEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public UUID getEventId() {
        return this.eventId;
    }

    public Instant getEventDate() {
        return this.eventDate;
    }

    public abstract String getEventKey();
}
