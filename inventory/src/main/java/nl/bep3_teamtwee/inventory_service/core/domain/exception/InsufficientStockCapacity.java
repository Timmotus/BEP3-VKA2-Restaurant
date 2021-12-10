package nl.bep3_teamtwee.inventory_service.core.domain.exception;

public class InsufficientStockCapacity extends RuntimeException {

    public InsufficientStockCapacity(String message) {
        super(message);
    }
}
