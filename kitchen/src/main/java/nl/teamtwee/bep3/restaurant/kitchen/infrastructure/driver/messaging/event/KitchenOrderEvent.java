package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KitchenOrderEvent {
    private final UUID eventId;
    private final String eventKey;
    private final Instant eventDate;
    private final UUID orderId;
    private final UUID reservationId;
}
