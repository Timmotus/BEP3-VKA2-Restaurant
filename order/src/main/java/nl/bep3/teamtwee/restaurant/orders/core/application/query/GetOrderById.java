package nl.bep3.teamtwee.restaurant.orders.core.application.query;

import java.util.UUID;

public class GetOrderById {
    private final UUID id;

    public GetOrderById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
