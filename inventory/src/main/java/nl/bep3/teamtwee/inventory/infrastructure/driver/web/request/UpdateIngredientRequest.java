package nl.bep3.teamtwee.inventory.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class UpdateIngredientRequest {
    @NotBlank public String productName, unit;
    @PositiveOrZero public int stock;
    @Positive public int capacity, purchaseCapacity, sellCapacity;
    @Positive public double purchasePrice, sellPrice;
}