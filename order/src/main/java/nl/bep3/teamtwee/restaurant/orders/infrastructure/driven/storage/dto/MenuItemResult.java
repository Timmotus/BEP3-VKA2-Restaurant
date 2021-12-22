package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResult {
    private Map<String, Long> items;
}
