package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderCompleted {
    private final UUID orderId;
    private final LocalDateTime receivedAt;
}
