package nl.bep3.teamtwee.restaurant.inventory.core.domain.exception;

public class IngredientNotFound extends RuntimeException {

    public IngredientNotFound(String message) {
        super(message);
    }
}
