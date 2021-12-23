package nl.teamtwee.bep3.restaurant.inventory.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.teamtwee.bep3.restaurant.inventory.core.domain.Unit;

@Getter
@AllArgsConstructor
public class RegisterIngredient {
    private final String productName;
    private final Unit unit;
    private final int capacity;
    private final int purchaseCapacity;
    private final int sellCapacity;
    private final double purchasePrice;
    private final double sellPrice;
}