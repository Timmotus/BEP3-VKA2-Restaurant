package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuItemResult {
    private String name;
    private Long price;
}
