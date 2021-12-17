package nl.bep3_teamtwee.inventory.core.application.query;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GetIngredientById {

    private final UUID id;

    public GetIngredientById(UUID id) {
        this.id = id;
    }

}
