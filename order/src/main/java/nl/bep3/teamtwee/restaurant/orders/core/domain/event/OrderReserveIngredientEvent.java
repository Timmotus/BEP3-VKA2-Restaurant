package nl.bep3.teamtwee.restaurant.orders.core.domain.event;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderReserveIngredientEvent extends OrderEvent {
    private final UUID orderId;
    private final Map<String, Integer> ingredients;

    @Override
    public String getEventKey() {
        return "";
    }
}
