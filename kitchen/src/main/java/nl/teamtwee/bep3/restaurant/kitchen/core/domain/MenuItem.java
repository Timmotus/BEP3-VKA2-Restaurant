package nl.teamtwee.bep3.restaurant.kitchen.core.domain;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuItem {
    private final UUID id;
    private final String name;
    private final Map<String, Long> ingredients;
}
