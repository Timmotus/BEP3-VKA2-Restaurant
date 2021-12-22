package nl.teamtwee.bep3.restaurant.menu.core.application.command;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.menu.core.domain.PizzaOptions;
import nl.teamtwee.bep3.restaurant.menu.core.domain.PizzaSizes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class AdminCreateMenu {
    private String name;
    private List<Ingredient> ingredients;
    private List<PizzaOptions> options;
    private PizzaSizes size;
    private double price;
    private int quantity;

    public AdminCreateMenu(
            String name, List<Ingredient> ingredients, List<PizzaOptions> options,
            PizzaSizes size, double price, int quantity) {
        this.name = name;
        this.ingredients = ingredients;
        this.options = options;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }
}
