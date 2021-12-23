package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.bep3.teamtwee.inventory.core.domain.Unit;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateIngredient {
    private final UUID id;
    private final String productName;
    private final Unit unit;
    private final int capacity;
    private final int stock;
    private final int purchaseCapacity;
    private final int sellCapacity;
    private final double purchasePrice;
    private final double sellPrice;
}