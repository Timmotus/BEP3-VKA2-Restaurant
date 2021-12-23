package nl.teamtwee.bep3.restaurant.kitchen.core.domain;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Document
@AllArgsConstructor
public class OrderItem {
    private String name;
    private Map<String, Long> ingredients;
}