package com.example.ginos.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class PizzaRequest {

  @NotBlank
  public String name;

  @NotBlank
  public List<String> ingredients;

  @Positive
  public int quantity;

  public double price;




}
