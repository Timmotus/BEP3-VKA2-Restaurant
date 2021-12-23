package nl.teamtwee.bep3.restaurant.delivery.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryStarted extends DeliveryEvent {
    private final UUID deliveryId;
    private final UUID orderId;

    @Override
    public String getEventKey() {
        return "delivery.order.started";
    }
}
