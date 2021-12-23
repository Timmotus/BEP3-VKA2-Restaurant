package nl.teamtwee.bep3.restaurant.inventory.core.application.query;

import lombok.Getter;

@Getter
public class FindIngredientsByProductName {
    private final String productName;
    private final String orderBy;
    private final String direction;

    public FindIngredientsByProductName(String productName, String orderBy, String direction) {
        if (orderBy == null) orderBy = "productName";
        if (direction == null) direction = "asc";
        this.productName = productName;
        this.orderBy = orderBy;
        this.direction = direction;
    }
}