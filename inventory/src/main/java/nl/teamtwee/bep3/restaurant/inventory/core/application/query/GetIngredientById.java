package nl.teamtwee.bep3.restaurant.inventory.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetIngredientById {
    private final UUID id;
}