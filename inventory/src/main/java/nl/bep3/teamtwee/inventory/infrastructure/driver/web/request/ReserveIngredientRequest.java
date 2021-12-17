package nl.bep3.teamtwee.inventory.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

public class ReserveIngredientRequest {
    @NotNull
    public UUID id;

    @PositiveOrZero
    public int amount;
}
