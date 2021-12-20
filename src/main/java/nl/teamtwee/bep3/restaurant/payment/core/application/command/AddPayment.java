package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddPayment {
    private final UUID orderId;

    public AddPayment(UUID orderId) {
        this.orderId = orderId;
    }



}
