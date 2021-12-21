package nl.teamtwee.bep3.restaurant.menu.core.application.command;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class AdminCreateMenu {
    private String name;
    private List<Ingredient> ingredients;
    private List<String> options;
    private double price;
    private int quantity;

    public AdminCreateMenu(String name, List<Ingredient> ingredients, List<String> options, double price, int quantity) {
        this.name = name;
        this.ingredients = ingredients;
        this.options = options;
        this.price = price;
        this.quantity = quantity;
    }
}
