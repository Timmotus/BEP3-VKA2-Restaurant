package nl.teamtwee.bep3.restaurant.payment.core.domain.exception;

public class PaymentNotFound extends RuntimeException{
    public PaymentNotFound(String message) {
        super(message);
    }
}
