package nl.teamtwee.bep3.restaurant.menu.core.domain;

import lombok.*;
import nl.teamtwee.bep3.restaurant.menu.core.domain.event.MenuEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
@ToString
@NoArgsConstructor
public class Pizza {
  @Id
  private UUID id;
  private String name;
  private List<Ingredient> ingredients;
  private List<PizzaOptions> options;
  private PizzaSizes size;
  private double price;
  private int quantity;

  @Transient
  private List<MenuEvent> events = new ArrayList<>();

  public Pizza(String name, List<Ingredient> ingredients, List<PizzaOptions> options, PizzaSizes size, double price, int quantity) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.ingredients = ingredients;
    this.options = options;
    this.size = size;
    this.price = price;
    this.quantity = quantity;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public List<MenuEvent> listEvents() {
    return events;
  }

  public void clearEvents() {
    this.events.clear();
  }
}
