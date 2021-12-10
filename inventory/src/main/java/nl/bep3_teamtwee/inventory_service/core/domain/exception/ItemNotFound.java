package nl.bep3_teamtwee.inventory_service.core.domain.exception;

public class ItemNotFound extends RuntimeException {

    public ItemNotFound(String message) {
        super(message);
    }
}
