package nl.teamtwee.bep3.restaurant.inventory.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BuyStockForIngredientWithId {
    private final UUID id;
}