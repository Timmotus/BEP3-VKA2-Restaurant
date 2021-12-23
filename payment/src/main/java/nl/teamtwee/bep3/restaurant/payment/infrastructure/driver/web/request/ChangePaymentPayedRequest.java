package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangePaymentPayedRequest {
    @NotBlank
    public boolean payed;
}
