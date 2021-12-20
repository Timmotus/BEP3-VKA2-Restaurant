package nl.teamtwee.bep3.restaurant.menu.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Ingredient {
  private String name;

  public Ingredient(String name) {
    this.name = name;
  }
}
