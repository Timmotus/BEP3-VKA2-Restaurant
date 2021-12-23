package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompleteOrderPayment {
    private UUID orderId;
    private UUID paymentId;
}
