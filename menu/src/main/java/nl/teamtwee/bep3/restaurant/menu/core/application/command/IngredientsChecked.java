package nl.teamtwee.bep3.restaurant.menu.core.application.command;

public class IngredientsChecked {
  private final boolean enoughIngredients;

  public IngredientsChecked(boolean enoughIngredients) {
    this.enoughIngredients = enoughIngredients;
  }

  public boolean checkEnoughIngredients() {
    return enoughIngredients;
  }
}
