package nl.teamtwee.bep3.restaurant.inventory.core.domain.exception;

public class InsufficientStockCapacity extends RuntimeException {
    public InsufficientStockCapacity(String message) {
        super(message);
    }
}