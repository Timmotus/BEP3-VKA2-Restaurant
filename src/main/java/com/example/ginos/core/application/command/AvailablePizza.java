package com.example.ginos.core.application.command;

import com.example.ginos.core.domain.Pizza;

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
