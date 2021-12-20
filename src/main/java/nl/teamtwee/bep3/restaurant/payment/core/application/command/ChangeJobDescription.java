package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import java.util.UUID;

public class ChangeJobDescription {
    private final UUID id;
    private final String description;

    public ChangeJobDescription(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
