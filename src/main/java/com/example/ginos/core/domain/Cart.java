package com.example.ginos.core.domain;

import com.example.ginos.core.domain.event.MenuEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Document
public class Cart {
    @Id
    private UUID cartId;
    private String customerName;
    private Map<Pizza, List<Ingredient>> selectedPizzas = new HashMap<>();
    private double currentPrice;
    private int quantity;
    private boolean finishedOrder;

    @Transient
    private List<MenuEvent> events = new ArrayList<>();


    public Cart(String customerName, Pizza selectedPizza, int quantity, boolean finishedOrder) {
      this.cartId = UUID.randomUUID();
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
