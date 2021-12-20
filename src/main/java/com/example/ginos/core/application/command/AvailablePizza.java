package com.example.ginos.core.application.command;

import com.example.ginos.core.domain.Pizza;

import java.util.List;
import java.util.UUID;

public class AvailablePizza {
//    private final UUID pizza;
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
