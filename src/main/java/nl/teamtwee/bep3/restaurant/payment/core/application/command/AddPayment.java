package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddPayment {
    private final UUID orderId;
    private double cost;

    public AddPayment(UUID orderId, double cost) {
        this.orderId = orderId;
        this.cost = cost;
    }


    public UUID getOrderId() {
        return orderId;
    }
}
