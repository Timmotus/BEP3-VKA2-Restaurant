package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import java.time.Instant;
import java.util.UUID;

import lombok.Getter;

@Getter
public abstract class PaymentEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public abstract String getEventKey();
}
