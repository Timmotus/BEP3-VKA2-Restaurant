package nl.teamtwee.bep3.restaurant.kitchen.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPreparationCompleted extends OrderEvent {
    private final UUID reservationId;
    private final UUID orderId;

    @Override
    public String getEventKey() {
        return "delivery.order.create";
    }
}
