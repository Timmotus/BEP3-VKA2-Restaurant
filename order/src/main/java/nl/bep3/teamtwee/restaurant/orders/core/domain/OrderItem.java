package nl.bep3.teamtwee.restaurant.orders.core.domain;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document
public class OrderItem {
    private String name;
    private Integer count;

    // name, amount
    private Map<String, Integer> ingredients;
    private List<OrderItemOption> options;

    public OrderItem(String name, Integer count, Map<String, Integer> ingredients, List<OrderItemOption> options) {
        this.name = name;
        this.count = count;
        this.ingredients = ingredients;
        this.options = options;
    }
}
