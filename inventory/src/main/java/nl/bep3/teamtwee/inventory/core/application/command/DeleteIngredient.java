package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteIngredient {
    private final UUID id;
}