package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BuyStockForIngredientWithId {

    private final UUID id;

    public BuyStockForIngredientWithId(UUID id) {
        this.id = id;
    }

}
