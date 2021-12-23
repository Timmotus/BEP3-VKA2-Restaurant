package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;

import java.util.UUID;

@Getter
public class ProceedWithOrder {
    private final UUID id;
    private final OrderStatus orderStatus;

    public ProceedWithOrder(UUID id, OrderStatus orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }
}