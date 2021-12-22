package nl.teamtwee.bep3.restaurant.delivery.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryStatus {
    public UUID deliveryId;
    public UUID orderId;
    public DeliveryStatusEnum deliveryStatusEnum;

    public DeliveryStatus(UUID orderId, DeliveryStatusEnum deliveryStatusEnum){
        this.orderId = orderId;
        this.deliveryStatusEnum = deliveryStatusEnum;
    }
}
