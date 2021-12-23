package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request;

import java.util.UUID;

public class CreatePaymentRequest {
    public UUID orderId;
    public Long amount;
}
