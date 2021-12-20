package nl.bep3.teamtwee.restaurant.inventory.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.event.IngredientEvent;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.exception.InsufficientStockCapacity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Ingredient {

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

    @Transient @Setter(value = AccessLevel.NONE)
    private final List<IngredientEvent> events = new ArrayList<>();

    public Ingredient(String productName, Unit unit, int capacity, int purchaseCapacity, int sellCapacity, double purchasePrice, double sellPrice) {
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

    public List<IngredientEvent> listEvents() {
        return events;
    }
}
