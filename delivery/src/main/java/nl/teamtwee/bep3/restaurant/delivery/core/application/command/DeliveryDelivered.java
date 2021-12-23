package nl.teamtwee.bep3.restaurant.delivery.core.application.command;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryDelivered {
    private final UUID deliveryId;
    private final LocalDateTime deliveredAt;
}
