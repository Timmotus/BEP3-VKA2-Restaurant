package nl.bep3_teamtwee.inventory_service.core.application.command;

import nl.bep3_teamtwee.inventory_service.core.domain.Unit;

import java.util.UUID;

public class RegisterItem {

    private String productName;
    private Unit unit;
    private int capacity;
    private int purchaseCapacity;
    private int sellCapacity;
    private double purchasePrice;
    private double sellPrice;

    public RegisterItem(String productName, Unit unit, int capacity, int purchaseCapacity, int sellCapacity, double purchasePrice, double sellPrice) {
        this.productName = productName;
        this.unit = unit;
        this.capacity = capacity;
        this.purchaseCapacity = purchaseCapacity;
        this.sellCapacity = sellCapacity;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
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
