package nl.bep3.teamtwee.kitchen.core.domain.exception;

public class OrderStatusException extends RuntimeException {
    public OrderStatusException(String message) {
        super(message);
    }
}