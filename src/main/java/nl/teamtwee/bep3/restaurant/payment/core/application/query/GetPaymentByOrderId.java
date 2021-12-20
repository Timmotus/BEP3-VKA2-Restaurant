package nl.teamtwee.bep3.restaurant.payment.core.application.query;

import java.util.UUID;

public class GetPaymentByOrderId {
    private final UUID orderId;

    public GetPaymentByOrderId(UUID id) {
        this.orderId = id;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
