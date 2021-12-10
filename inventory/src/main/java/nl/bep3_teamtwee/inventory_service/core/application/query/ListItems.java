package nl.bep3_teamtwee.inventory_service.core.application.query;

public class ListItems {

    private final String orderBy;
    private final String direction;

    public ListItems(String orderBy, String direction) {
        if (orderBy == null) orderBy = "productName";
        if (direction == null) direction = "asc";

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
