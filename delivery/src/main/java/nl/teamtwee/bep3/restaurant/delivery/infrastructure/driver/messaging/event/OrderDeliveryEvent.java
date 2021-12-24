package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class OrderDeliveryEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID reservationId;
    public UUID orderId;
}
