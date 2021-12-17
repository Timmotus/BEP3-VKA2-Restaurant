package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteIngredient {

    private final UUID id;

    public DeleteIngredient(UUID id) {
        this.id = id;
    }

}
