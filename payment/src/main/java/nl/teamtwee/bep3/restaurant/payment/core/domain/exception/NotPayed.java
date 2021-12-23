package nl.teamtwee.bep3.restaurant.payment.core.domain.exception;

public class NotPayed extends RuntimeException {
    public NotPayed(String message) {
        super(message);
    }
}
