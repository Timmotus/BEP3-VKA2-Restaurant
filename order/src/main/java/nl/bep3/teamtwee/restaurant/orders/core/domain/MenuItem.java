package nl.bep3.teamtwee.restaurant.orders.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuItem {
    private String name;
    private Double price;
}
