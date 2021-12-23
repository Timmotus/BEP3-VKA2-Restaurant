package nl.bep3.teamtwee.restaurant.inventory.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RegisterIngredientRequest {
    @NotBlank
    public String productName;

    @NotBlank
    public String unit;

    @Positive
    public int capacity;

    @Positive
    public int purchaseCapacity;

    @Positive
    public int sellCapacity;

    @Positive
    public double purchasePrice;

    @Positive
    public double sellPrice;
}
