package nl.bep3.teamtwee.restaurant.orders.core.domain.event;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderMenuItemAvailableEvent extends OrderEvent {
    private final Set<String> pizzaNames;

    @Override
    public String getEventKey() {
        return "pizza.available";
    }
}
