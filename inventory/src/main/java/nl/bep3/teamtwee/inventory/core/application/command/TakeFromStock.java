package nl.bep3.teamtwee.inventory.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TakeFromStock {
    private Map<UUID, Integer> ingredientAmountMap;
}