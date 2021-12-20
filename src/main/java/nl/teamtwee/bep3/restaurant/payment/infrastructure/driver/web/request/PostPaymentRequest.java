package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class PostPaymentRequest {
    @NotBlank
    public UUID orderId;

}
