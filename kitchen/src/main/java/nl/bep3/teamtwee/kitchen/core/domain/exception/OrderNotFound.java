package nl.bep3.teamtwee.kitchen.core.domain.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message) {
        super(message);
    }
}