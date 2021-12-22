package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.response;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;

@Getter
public class MenuItemResponse {
    private UUID id;
    private String name;
    private Map<String, Long> ingredients;
    private Double price;

    public MenuItemResponse(MenuItem item) {
        this.id = item.getId();
        this.name = item.getName();
        this.ingredients = item.getIngredients();
        this.price = item.getPrice();
    }
}
