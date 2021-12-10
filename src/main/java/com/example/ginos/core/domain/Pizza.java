package com.example.ginos.core.domain;

import com.example.ginos.core.domain.event.MenuEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Document
public class Pizza {
    private UUID id;
    private  String name;
    private List<Ingredient> ingredients;
    private double price;
    private int quantity;

    @Transient
    private List<MenuEvent> events = new ArrayList<>();

    public Pizza(String name, List<Ingredient> ingredients, double price, int quantity) {
        this. id = UUID.randomUUID();
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.quantity =   quantity;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<MenuEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
