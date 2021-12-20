package nl.teamtwee.bep3.restaurant.payment.core.application.query;

public class ListJobs {
    private final String orderBy;
    private final String direction;

    public ListJobs(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

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
