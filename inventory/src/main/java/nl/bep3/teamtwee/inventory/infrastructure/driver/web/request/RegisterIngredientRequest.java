package nl.bep3.teamtwee.inventory.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RegisterIngredientRequest {
    @NotBlank public String productName, unit;
    @Positive public int capacity, purchaseCapacity, sellCapacity;
    @Positive public double purchasePrice, sellPrice;
}