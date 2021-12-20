package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging.event;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class PizzaKeywordEvent {

  public UUID eventId;
  public String eventKey;
  public Instant eventDate;
  public List<Pizza> pizza;
  public String keyword;
  public boolean enoughIngredients;

}
