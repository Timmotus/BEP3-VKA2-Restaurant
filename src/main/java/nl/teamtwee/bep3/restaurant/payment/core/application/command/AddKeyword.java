package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import java.util.UUID;

public class AddKeyword {
    private final UUID id;
    private final String keyword;

    public AddKeyword(UUID id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public UUID getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }
}
