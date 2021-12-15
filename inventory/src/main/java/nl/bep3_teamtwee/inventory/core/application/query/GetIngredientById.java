package nl.bep3_teamtwee.inventory.core.application.query;

import java.util.UUID;

public class GetIngredientById {

    private final UUID id;

    public GetIngredientById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
