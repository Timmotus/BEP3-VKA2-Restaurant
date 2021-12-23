package nl.teamtwee.bep3.restaurant.payment.core.domain.event;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentPaid extends PaymentEvent {
    private final UUID paymentId;
    private final UUID orderId;

    @Override
    public String getEventKey() {
        return "payments.order.completed";
    }
}
