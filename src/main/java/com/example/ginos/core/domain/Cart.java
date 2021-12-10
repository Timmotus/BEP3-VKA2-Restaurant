package com.example.ginos.core.domain;

import com.example.ginos.core.domain.event.MenuEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private String customerName;
    private Map<Pizza, List<Ingredient>> selectedPizzas;
    private double currentPrice;
    private int quantity;
    private boolean finishedOrder;

    @Transient
    private List<MenuEvent> events = new ArrayList<>();


    public Cart(String customerName, Pizza selectedPizza, int quantity, boolean finishedOrder) {
        this.customerName = customerName;
        selectedPizzas.put(selectedPizza, selectedPizza.getIngredients());
        this.finishedOrder = finishedOrder;
        for (Map.Entry<Pizza,List<Ingredient>> entry : selectedPizzas.entrySet()){
            currentPrice = entry.getKey().getPrice() * quantity;
            System.out.println(currentPrice);
        }
    }

    public List<MenuEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

}
