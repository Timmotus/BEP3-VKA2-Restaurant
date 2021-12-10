package nl.bep3_teamtwee.inventory_service.core.domain;

import nl.bep3_teamtwee.inventory_service.core.domain.event.ItemEvent;
import nl.bep3_teamtwee.inventory_service.core.domain.exception.InsufficientStockCapacity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Item {

    @Id
    private UUID id;

    @Indexed
    private String productName;

    private Unit unit;

    private int stock;
    private int capacity;
    private int purchaseCapacity;
    private int sellCapacity;
    private double purchasePrice;
    private double sellPrice;

    @Transient
    private List<ItemEvent> events = new ArrayList<>();

    public Item(String productName, Unit unit, int capacity, int purchaseCapacity, int sellCapacity, double purchasePrice, double sellPrice) {
        this.id = UUID.randomUUID();
        this.stock = 0;
        this.productName = productName;
        this.unit = unit;
        this.capacity = capacity;
        this.purchaseCapacity = purchaseCapacity;
        this.sellCapacity = sellCapacity;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getStock() {
        return stock;
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

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPurchaseCapacity(int purchaseCapacity) {
        this.purchaseCapacity = purchaseCapacity;
    }

    public void setSellCapacity(int sellCapacity) {
        this.sellCapacity = sellCapacity;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    // Methods
    public void buyStock() {
        if (stock + purchaseCapacity > capacity)
            throw new InsufficientStockCapacity(
                    String.format("Capacity of Item: %s is: %s! Buying: %s while current Stock is: %s results in: %s overflow!",
                    id, capacity, purchaseCapacity, stock, capacity - stock + purchaseCapacity));
        this.stock += this.purchaseCapacity;
    }

    // Event Methods
    public void clearEvents() {
        this.events.clear();
    }

    public List<ItemEvent> listEvents() {
        return events;
    }
}
