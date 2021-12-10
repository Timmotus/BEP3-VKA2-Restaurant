package com.example.ginos.core.domain;

import com.example.ginos.core.domain.event.MenuEvent;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Document
public class Order {

    @Id
    private UUID id;
    private String customerName;
    @Indexed
    private Map<Pizza, List<Ingredient>> pizzaListMap;
    private double totalPrice;

    @Transient
    private List<MenuEvent> events = new ArrayList<>();

    public Order(String customerName, Map<Pizza, List<Ingredient>> pizzaListMap , double totalPrice) {
        this.id = UUID.randomUUID();
        this.customerName= customerName;
        this.pizzaListMap = pizzaListMap;
        this.totalPrice = totalPrice;
    }

    public List<MenuEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
