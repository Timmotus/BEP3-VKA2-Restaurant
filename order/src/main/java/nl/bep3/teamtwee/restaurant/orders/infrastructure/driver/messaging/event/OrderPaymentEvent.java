package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class OrderPaymentEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID order;
    public UUID payment;
}
