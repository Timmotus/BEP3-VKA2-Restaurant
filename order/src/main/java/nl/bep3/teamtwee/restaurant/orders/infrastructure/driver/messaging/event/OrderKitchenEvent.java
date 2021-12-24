package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderKitchenEvent {
    private final UUID eventId;
    private final String eventKey;
    private final Instant eventDate;
    private final UUID orderId;
    private final UUID reservationId;
}
