package com.example.ginos.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Pizza(String name, List<Ingredient> ingredients, double price) {
        this. id = UUID.randomUUID();
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
}
