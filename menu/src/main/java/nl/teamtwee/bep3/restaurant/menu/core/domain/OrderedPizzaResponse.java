package nl.teamtwee.bep3.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class OrderedPizzaResponse {
    private Map<String, Long> items;
}