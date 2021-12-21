package nl.teamtwee.bep3.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedPizzaResponse {
    private List<Pizza> pizzasAndTheirPrice;
    private List<String> pizzasWhichDontExist;
    private boolean orderCanBeMade;
}