package nl.teamtwee.bep3.restaurant.delivery.core.application.query;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetDeliveryByOrderId {
    private UUID orderId;
}
