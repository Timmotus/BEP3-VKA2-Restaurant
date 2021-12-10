package nl.bep3_teamtwee.inventory_service.core.application.command;

import java.util.UUID;

public class BuyStockForItemWithId {

    private UUID id;

    public BuyStockForItemWithId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
