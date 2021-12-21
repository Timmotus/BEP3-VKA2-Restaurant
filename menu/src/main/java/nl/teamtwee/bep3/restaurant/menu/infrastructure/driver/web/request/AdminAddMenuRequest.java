package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Ingredient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class AdminAddMenuRequest {
    @NotBlank
    public String name;

    public List<Ingredient> ingredients;

    public List<String> options;

    @Positive
    public double price;

    @Positive
    public int quantity;

}
