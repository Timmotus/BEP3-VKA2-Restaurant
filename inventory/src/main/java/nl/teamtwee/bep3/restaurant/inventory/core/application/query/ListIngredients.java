package nl.teamtwee.bep3.restaurant.inventory.core.application.query;

import lombok.Getter;

@Getter
public class ListIngredients {
    private final String orderBy;
    private final String direction;

    public ListIngredients(String orderBy, String direction) {
        if (orderBy == null) orderBy = "productName";
        if (direction == null) direction = "asc";

        this.orderBy = orderBy;
        this.direction = direction;
    }
}