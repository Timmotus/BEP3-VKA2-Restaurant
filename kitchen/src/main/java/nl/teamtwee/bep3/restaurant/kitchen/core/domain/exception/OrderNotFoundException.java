package nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}