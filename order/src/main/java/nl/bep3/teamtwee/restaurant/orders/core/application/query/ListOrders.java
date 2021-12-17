package nl.bep3.teamtwee.restaurant.orders.core.application.query;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;

import nl.bep3.teamtwee.restaurant.orders.core.common.annotations.ValueOfEnum;

public class ListOrders {
    private final String orderBy;

    @Valid
    @ValueOfEnum(enumClass = Sort.Direction.class)
    private final String direction;

    public ListOrders(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "id";
        }
        if (direction == null) {
            direction = Sort.Direction.ASC.toString();
        }

        this.orderBy = orderBy;
        this.direction = direction.toUpperCase();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
