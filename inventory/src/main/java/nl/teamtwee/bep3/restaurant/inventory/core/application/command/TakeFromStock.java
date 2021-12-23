package nl.teamtwee.bep3.restaurant.inventory.core.application.command;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TakeFromStock {
    private final Map<String, Integer> ingredientAmountMap;
}