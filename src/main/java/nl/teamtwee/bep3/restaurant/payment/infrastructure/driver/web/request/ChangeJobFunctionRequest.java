package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangeJobFunctionRequest {
    @NotBlank
    public String function;
}
