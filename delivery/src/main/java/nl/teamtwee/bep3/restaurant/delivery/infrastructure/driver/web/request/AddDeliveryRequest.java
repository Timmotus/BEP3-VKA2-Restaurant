package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web.request;

import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class AddDeliveryRequest {
    @NotNull
    private UUID orderId;
    @NotBlank
    private Enum<DeliveryStatusEnum> deliveryStatusEnum;
}
