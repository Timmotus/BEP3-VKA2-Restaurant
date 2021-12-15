package nl.bep3_teamtwee.inventory.core.domain.exception;

public class InsufficientStockCapacity extends RuntimeException {

    public InsufficientStockCapacity(String message) {
        super(message);
    }
}
