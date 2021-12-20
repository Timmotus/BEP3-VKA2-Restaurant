package nl.teamtwee.bep3.restaurant.payment.core.application.query;

import java.util.UUID;

public class GetPaymentById {
    private final UUID id;

    public GetPaymentById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
