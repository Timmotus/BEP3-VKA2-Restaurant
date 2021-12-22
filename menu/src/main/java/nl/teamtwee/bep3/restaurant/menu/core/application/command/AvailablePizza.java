package nl.teamtwee.bep3.restaurant.menu.core.application.command;

import java.util.List;

public class AvailablePizza {
    private final List<String> pizzaNames;

    public AvailablePizza(List<String> pizzaNames) {
        this.pizzaNames = pizzaNames;
    }

    public List<String> getPizzas() {
        return pizzaNames;
    }
}
