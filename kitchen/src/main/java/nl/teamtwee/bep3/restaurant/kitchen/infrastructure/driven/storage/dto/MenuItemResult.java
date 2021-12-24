package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.dto;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResult {
    private UUID id;
    private String name;
    private Double price;
    private Map<String, Long> ingredients;
}
