package nl.teamtwee.bep3.restaurant.menu.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.menu.core.domain.event.MenuEvent;

@Getter
@Document
public class MenuItem {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    private Map<String, Long> ingredients;
    private Double price;

    @Transient
    private List<MenuEvent> events = new ArrayList<>();

    public MenuItem(String name, Map<String, Long> ingredients, Double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public List<MenuEvent> listEvents() {
        return this.events;
    }
}
