package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PrepareOrder {
    private final UUID reservationId;
    private final UUID orderId;
}