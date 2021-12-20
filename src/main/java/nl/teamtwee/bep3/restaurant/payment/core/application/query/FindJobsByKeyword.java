package nl.teamtwee.bep3.restaurant.payment.core.application.query;

public class FindJobsByKeyword {
    private final String keyword;
    private final String orderBy;
    private final String direction;

    public FindJobsByKeyword(String keyword, String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.keyword = keyword;
        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
