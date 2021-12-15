package nl.bep3_teamtwee.inventory.core.application.command;

import java.util.UUID;

public class DeleteIngredient {

    private final UUID id;

    public DeleteIngredient(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
