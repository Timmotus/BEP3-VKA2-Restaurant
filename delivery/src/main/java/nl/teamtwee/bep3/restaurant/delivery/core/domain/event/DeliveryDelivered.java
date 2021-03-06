package nl.teamtwee.bep3.restaurant.delivery.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryDelivered extends DeliveryEvent {
    private final UUID deliveryId;
    private final UUID orderId;

    @Override
    public String getEventKey() {
        return "order.delivery.delivered";
    }
}
