package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
public class PaymentEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();
    private String eventKey;
    private UUID orderId;
    private UUID paymentId;

    public PaymentEvent(String eventKey, UUID orderId, UUID paymentId) {
        this.eventKey = eventKey;
        this.orderId = orderId;
        this.paymentId = paymentId;
    }


}
