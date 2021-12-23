package nl.teamtwee.bep3.restaurant.delivery.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;

@Getter
@Document
public class Delivery {
    @Id
    private UUID id;
    private UUID orderId;
    private DeliveryStatus status;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    public Delivery(UUID orderId) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.status = DeliveryStatus.DELIVERING;
        // maybe throw event delivery was created
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
