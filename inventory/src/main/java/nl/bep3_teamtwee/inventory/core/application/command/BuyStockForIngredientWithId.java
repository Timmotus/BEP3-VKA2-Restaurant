package nl.bep3_teamtwee.inventory.core.application.command;

import java.util.UUID;

public class BuyStockForIngredientWithId {

    private final UUID id;

    public BuyStockForIngredientWithId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
