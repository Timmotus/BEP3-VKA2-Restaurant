package com.example.ginos.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private String customerName;
    private Map<Pizza, List<Ingredient>> selectedPizzas;
    private double currentPrice;
    private boolean finishedOrder;

    public Cart(String customerName, Map<Pizza, List<Ingredient>> selectedPizzas, double currentPrice, boolean finishedOrder) {
        this.customerName = customerName;
        this.selectedPizzas = selectedPizzas;
        this.currentPrice = currentPrice;
        this.finishedOrder = finishedOrder;
    }
}
