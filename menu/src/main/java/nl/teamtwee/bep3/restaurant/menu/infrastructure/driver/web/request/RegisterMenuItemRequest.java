package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class RegisterMenuItemRequest {
    @NotBlank
    public String name;

    @NotEmpty
    public Map<String, Long> ingredients;

    @Positive
    public Double price;
}
