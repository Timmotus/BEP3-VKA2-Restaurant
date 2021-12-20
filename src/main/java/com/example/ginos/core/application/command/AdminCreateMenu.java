package com.example.ginos.core.application.command;

import com.example.ginos.core.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class AdminCreateMenu{
        private  String name;
        private List<Ingredient> ingredients;
        private double price;
        private int quantity;

        public AdminCreateMenu(String name, List<Ingredient> ingredients, double price, int quantity) {
            this.name = name;
            this.ingredients = ingredients;
            this.price = price;
            this.quantity =   quantity;
        }
}
