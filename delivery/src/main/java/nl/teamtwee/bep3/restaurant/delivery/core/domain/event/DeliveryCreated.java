package nl.teamtwee.bep3.restaurant.delivery.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryCreated extends DeliveryEvent {
    private UUID orderId;

    @Override
    public String getEventKey() {
        return "order.delivery.created";
    }
}
