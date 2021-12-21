package nl.teamtwee.bep3.restaurant.delivery.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryStatus {
    @NotBlank
    public Enum<DeliveryStatusEnum> deliveryStatusEnum;
}
