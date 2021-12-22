package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class OrderedPizzaRequest {
    @NotBlank
    private String name;
}