package nl.teamtwee.bep3.restaurant.inventory.core.domain.exception;

public class InsufficientStock extends RuntimeException {
    public InsufficientStock(String message) {
        super(message);
    }
}