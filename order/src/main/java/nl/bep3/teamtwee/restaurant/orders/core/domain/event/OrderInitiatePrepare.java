package nl.bep3.teamtwee.restaurant.orders.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderInitiatePrepare extends OrderEvent {
    private final UUID orderId;
    private final UUID reservationId;

    @Override
    public String getEventKey() {
        return "kitchen.order.prepare";
    }
}
