package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.UUID;

public class InvalidateOrderPayment {
    private UUID order;
    private UUID payment;

    public InvalidateOrderPayment(UUID order, UUID payment) {
        this.order = order;
        this.payment = payment;
    }

    public UUID getOrder() {
        return this.order;
    }

    public UUID getPayment() {
        return this.payment;
    }
}
