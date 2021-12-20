package nl.teamtwee.bep3.restaurant.menu.core.application.command;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;

import java.util.List;

public class AvailablePizza {
    private final String keyword;
    private final List<Pizza> pizzas;

    public AvailablePizza(List<Pizza> pizzas, String keyword) {
        this.pizzas = pizzas;
        this.keyword = keyword;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public String getKeyword() {
        return keyword;
    }
}
