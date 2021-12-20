package nl.teamtwee.bep3.restaurant.payment.core.application.query;

import java.util.UUID;

public class GetJobById {
    private final UUID id;

    public GetJobById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
