package nl.teamtwee.bep3.restaurant.inventory.core.domain.exception;

public class IngredientNotFound extends RuntimeException {
    public IngredientNotFound(String message) {
        super(message);
    }
}