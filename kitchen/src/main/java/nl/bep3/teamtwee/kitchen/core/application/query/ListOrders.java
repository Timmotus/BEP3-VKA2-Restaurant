package nl.bep3.teamtwee.kitchen.core.application.query;

import lombok.Getter;

@Getter
public class ListOrders {
    private final String orderBy;
    private final String direction;

    public ListOrders(String orderBy, String direction) {
        if (orderBy == null) orderBy = "orderStatus";
        if (direction == null) direction = "asc";

        this.orderBy = orderBy;
        this.direction = direction;
    }
}