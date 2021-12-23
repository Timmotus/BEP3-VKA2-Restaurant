package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDeliveryDelivered {
    private UUID orderId;
    private UUID deliveryId;
}
