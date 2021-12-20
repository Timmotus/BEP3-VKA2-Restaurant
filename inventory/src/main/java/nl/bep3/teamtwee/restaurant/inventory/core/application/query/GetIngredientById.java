package nl.bep3.teamtwee.restaurant.inventory.core.application.query;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GetIngredientById {

    private final UUID id;

    public GetIngredientById(UUID id) {
        this.id = id;
    }

}
