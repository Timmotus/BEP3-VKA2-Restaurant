package nl.bep3.teamtwee.restaurant.inventory.core.domain.exception;

public class InsufficientStockCapacity extends RuntimeException {

    public InsufficientStockCapacity(String message) {
        super(message);
    }
}
