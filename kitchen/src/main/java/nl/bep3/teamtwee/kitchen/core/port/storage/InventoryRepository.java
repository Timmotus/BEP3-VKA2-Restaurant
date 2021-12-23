package nl.bep3.teamtwee.kitchen.core.port.storage;

import java.util.Map;
import java.util.UUID;

public interface InventoryRepository {
    void removeStock(Map<UUID, Integer> ingredientAmountMap);
}