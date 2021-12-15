package nl.bep3.teamtwee.restaurant.orders.core.domain.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message) {
        super(message);
    }
}
