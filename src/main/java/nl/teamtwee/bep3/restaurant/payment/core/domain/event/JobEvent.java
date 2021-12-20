package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import java.time.Instant;
import java.util.UUID;

public abstract class JobEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public UUID getEventId() {
        return eventId;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public abstract String getEventKey();
}
