package nl.bep3_teamtwee.inventory.core.application.command;

import nl.bep3_teamtwee.inventory.core.domain.Unit;

import java.util.UUID;

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

    public UpdateIngredient(UUID id, String productName, Unit unit, int stock, int capacity, int purchaseCapacity, int sellCapacity, double purchasePrice, double sellPrice) {
        this.id = id;
        this.productName = productName;
        this.unit = unit;
        this.stock = stock;
        this.capacity = capacity;
        this.purchaseCapacity = purchaseCapacity;
        this.sellCapacity = sellCapacity;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    public UUID getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getProductName() {
        return productName;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPurchaseCapacity() {
        return purchaseCapacity;
    }

    public int getSellCapacity() {
        return sellCapacity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }
}
