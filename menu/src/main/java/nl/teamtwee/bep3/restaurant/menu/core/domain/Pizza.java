package nl.teamtwee.bep3.restaurant.menu.core.domain;

import nl.teamtwee.bep3.restaurant.menu.core.domain.event.MenuEvent;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.PizzaRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Pizza {
  @Id
  private UUID id;
  private String name;
  private List<Ingredient> ingredients;
  private List<String> options;
  private double price;
  private int quantity;

  @Transient
  private List<MenuEvent> events = new ArrayList<>();

  public Pizza(String pizzaName, double price) {
    this.name = pizzaName;
    this.price = price;
  }

  public Pizza(String pizzaName, List<String> options) {
    this.name = String.valueOf(pizzaName);
    this.options = options;
  }

  public Pizza(String name, List<Ingredient> ingredients, double price, int quantity) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.ingredients = ingredients;
    this.price = price;
    this.quantity = quantity;
  }

  public Pizza(PizzaRequest pizzaRequest) {
    this.id = UUID.randomUUID();
    this.name = pizzaRequest.name;
    this.ingredients = new ArrayList<>();
    for (String string : pizzaRequest.ingredients) {
      this.ingredients.add(new Ingredient(string));
    }
    this.price = pizzaRequest.price;
    this.quantity = pizzaRequest.quantity;

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
