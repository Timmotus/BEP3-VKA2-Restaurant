package nl.teamtwee.bep3.restaurant.menu.core.domain.exception;

public class ItemNotFound extends RuntimeException {

    public ItemNotFound(String message) {
        super(message);
    }
}
