package nl.teamtwee.bep3.restaurant.delivery.core.application.query;

import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;

@Getter
@Setter
public class GetDeliveryByStatus {
    private Enum<DeliveryStatusEnum> deliveryStatusEnum;

    public GetDeliveryByStatus(Enum<DeliveryStatusEnum> deliveryStatusEnum) {
        this.deliveryStatusEnum = deliveryStatusEnum;
    }
}
