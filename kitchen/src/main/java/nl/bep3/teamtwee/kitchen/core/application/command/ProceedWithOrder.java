package nl.bep3.teamtwee.kitchen.core.application.command;

import lombok.Getter;
import nl.bep3.teamtwee.kitchen.core.domain.OrderStatus;

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