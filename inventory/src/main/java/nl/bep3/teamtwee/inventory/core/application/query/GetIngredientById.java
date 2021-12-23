package nl.bep3.teamtwee.inventory.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetIngredientById {
    private final UUID id;
}