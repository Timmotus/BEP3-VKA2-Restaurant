package nl.teamtwee.bep3.restaurant.kitchen.core.port.storage;

import java.util.Map;

public interface InventoryRepository {
    void removeStock(Map<String, Long> ingredientAmountMap);
}