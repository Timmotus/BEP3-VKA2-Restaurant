package nl.bep3.teamtwee.restaurant.orders.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document
public class OrderItemOption {
    private String name;
    private Integer multiplier;
    private String affectedIngredient;

    public OrderItemOption(String name, Integer multiplier, String affectedIngredient) {
        this.name = name;
        this.multiplier = multiplier;
        this.affectedIngredient = affectedIngredient;
    }
}
