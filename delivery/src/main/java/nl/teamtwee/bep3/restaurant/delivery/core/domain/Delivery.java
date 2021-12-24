package nl.teamtwee.bep3.restaurant.delivery.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryStarted;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryDelivered;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;

@Getter
@Document
public class Delivery {
    @Id
    private UUID id;
    private UUID orderId;
    private DeliveryStatus status;
    private LocalDateTime startedAt;
    @Setter
    private LocalDateTime deliveredAt;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    public Delivery(UUID orderId) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.startedAt = LocalDateTime.now();
        this.status = DeliveryStatus.DELIVERING;
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public void startDelivery(UUID orderId) {
        this.events.add(new DeliveryStarted(this.id, orderId));
    }

    public void deliver(LocalDateTime deliveredAt) {
        this.status = DeliveryStatus.DELIVERED;
        this.deliveredAt = deliveredAt;
        this.events.add(new DeliveryDelivered(this.id, orderId));
    }
}
