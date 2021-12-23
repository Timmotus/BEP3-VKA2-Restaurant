package nl.bep3.teamtwee.restaurant.orders.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderInitiateDelivery extends OrderEvent {
    private final UUID orderId;

    @Override
    public String getEventKey() {
        return "delivery.order.create";
    }
}
