package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;

@Getter
@AllArgsConstructor
public class ProceedWithOrder {
    private final UUID id;
    private final OrderStatus orderStatus;
}