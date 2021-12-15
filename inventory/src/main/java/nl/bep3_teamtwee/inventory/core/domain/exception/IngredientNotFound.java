package nl.bep3_teamtwee.inventory.core.domain.exception;

public class IngredientNotFound extends RuntimeException {

    public IngredientNotFound(String message) {
        super(message);
    }
}
