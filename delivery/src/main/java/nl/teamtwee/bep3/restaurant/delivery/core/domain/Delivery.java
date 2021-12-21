package nl.teamtwee.bep3.restaurant.delivery.core.domain;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Document
public class Delivery {
    @Id
    private UUID id;
    private UUID orderId;
    private Enum<DeliveryStatusEnum> deliveryStatusEnum;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    public Delivery(UUID orderId, Enum<DeliveryStatusEnum> deliveryStatusEnum) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.deliveryStatusEnum = deliveryStatusEnum;
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
