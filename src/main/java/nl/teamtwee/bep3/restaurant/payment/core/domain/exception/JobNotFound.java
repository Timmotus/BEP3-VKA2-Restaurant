package nl.teamtwee.bep3.restaurant.payment.core.domain.exception;

public class JobNotFound extends RuntimeException {
    public JobNotFound(String message) {
        super(message);
    }
}
