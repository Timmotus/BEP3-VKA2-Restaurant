package com.example.ginos.infrastructure.driver.messaging.event;

import com.example.ginos.core.domain.Pizza;

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
