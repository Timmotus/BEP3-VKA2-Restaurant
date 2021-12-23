package nl.teamtwee.bep3.restaurant.payment.core.application.query;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class GetPaymentByOrderId {
    private final UUID orderId;

    public GetPaymentByOrderId(UUID id) {
        this.orderId = id;
    }

}
