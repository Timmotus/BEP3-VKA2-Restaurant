package nl.bep3_teamtwee.inventory.core.application.query;

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

    public String getProductName() {
        return productName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
