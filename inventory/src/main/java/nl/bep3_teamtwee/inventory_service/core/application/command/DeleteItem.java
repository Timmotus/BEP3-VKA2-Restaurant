package nl.bep3_teamtwee.inventory_service.core.application.command;

import java.util.UUID;

public class DeleteItem {

    private UUID id;

    public DeleteItem(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
