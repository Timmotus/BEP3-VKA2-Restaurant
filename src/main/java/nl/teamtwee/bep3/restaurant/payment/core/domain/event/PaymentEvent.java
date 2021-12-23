package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
public abstract class PaymentEvent {
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
