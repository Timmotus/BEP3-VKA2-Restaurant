package nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception;

public class OrderStatusException extends RuntimeException {
    public OrderStatusException(String message) {
        super(message);
    }
}