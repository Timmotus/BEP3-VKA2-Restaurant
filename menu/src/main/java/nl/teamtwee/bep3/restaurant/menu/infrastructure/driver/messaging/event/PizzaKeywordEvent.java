package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class PizzaKeywordEvent {
  public UUID eventId;
  public String eventKey;
  public Instant eventDate;
  public List<String> pizzaNames;
  public boolean enoughIngredients;
}
