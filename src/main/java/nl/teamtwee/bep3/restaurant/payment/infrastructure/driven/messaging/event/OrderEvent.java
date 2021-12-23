package nl.teamtwee.bep3.restaurant.payment.infrastructure.driven.messaging.event;

import java.util.UUID;

public class OrderEvent {
    public UUID eventId;
    public UUID orderId;
    public UUID paymentId;
    public int cost;
}
