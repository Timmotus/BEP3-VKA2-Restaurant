package nl.teamtwee.bep3.restaurant.menu.core.application.command;

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
