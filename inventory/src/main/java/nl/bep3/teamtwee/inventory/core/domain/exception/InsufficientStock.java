package nl.bep3.teamtwee.inventory.core.domain.exception;

public class InsufficientStock extends RuntimeException {
    public InsufficientStock(String message) {
        super(message);
    }
}