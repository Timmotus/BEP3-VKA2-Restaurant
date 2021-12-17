package nl.bep3.teamtwee.restaurant.orders.core.domain;

import java.util.List;
import java.util.Map;

public class OrderItem {
    private String name;
    private Integer count;

    // name, amount
    private Map<String, Integer> ingredients;
    private List<OrderItemOption> options;
}
