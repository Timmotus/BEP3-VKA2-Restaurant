package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web.request;


import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;
import javax.validation.constraints.NotBlank;


public class DeliveryStatusRequest {
    @NotBlank
    public Enum<DeliveryStatusEnum> deliveryStatusEnum;
}
