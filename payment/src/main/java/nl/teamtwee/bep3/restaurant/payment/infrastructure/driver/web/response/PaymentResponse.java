package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.response;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;

import java.util.UUID;

@Getter
public class PaymentResponse {
    private final UUID paymentId;

    public PaymentResponse(Payment payment){
        this.paymentId = payment.getId();
    }
}
