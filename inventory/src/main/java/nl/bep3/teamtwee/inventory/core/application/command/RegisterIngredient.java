package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.Getter;
import nl.bep3.teamtwee.inventory.core.domain.Unit;

@Getter
public class RegisterIngredient {

    private final String productName;
    private final Unit unit;
    private final int capacity;
    private final int purchaseCapacity;
    private final int sellCapacity;
    private final double purchasePrice;
    private final double sellPrice;

    public RegisterIngredient(String productName, Unit unit, int capacity, int purchaseCapacity, int sellCapacity, double purchasePrice, double sellPrice) {
        this.productName = productName;
        this.unit = unit;
        this.capacity = capacity;
        this.purchaseCapacity = purchaseCapacity;
        this.sellCapacity = sellCapacity;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

}
