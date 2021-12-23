package nl.teamtwee.bep3.restaurant.kitchen.core.port.storage;

import java.util.Map;
import java.util.UUID;

public interface InventoryRepository {
    void removeStock(Map<UUID, Integer> ingredientAmountMap);
}