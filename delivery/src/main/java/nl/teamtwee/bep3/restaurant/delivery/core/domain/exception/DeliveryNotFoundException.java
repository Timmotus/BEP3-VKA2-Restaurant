package nl.teamtwee.bep3.restaurant.delivery.core.domain.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
