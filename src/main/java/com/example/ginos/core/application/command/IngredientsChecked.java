package com.example.ginos.core.application.command;

import java.util.UUID;

public class IngredientsChecked {

  private final boolean enoughIngredients;
  private final String keyword;

  public IngredientsChecked(boolean enoughIngredients, String keyword) {
    this.enoughIngredients = enoughIngredients;
    this.keyword = keyword;
  }

  public boolean checkEnoughIngredients() {
    return enoughIngredients;
  }

  public String getKeyword() {
    return keyword;
  }
}
