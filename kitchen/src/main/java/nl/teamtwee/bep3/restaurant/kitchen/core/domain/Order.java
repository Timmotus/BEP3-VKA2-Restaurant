package nl.teamtwee.bep3.restaurant.kitchen.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderEvent;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderPreparationCompleted;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderPreparationStarted;

@Getter
@Setter
@Document
public class Order {
    @Id
    private UUID id;
    private UUID orderId;

    private OrderStatus orderStatus;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private List<OrderItem> orderItems;

    @Transient
    @Setter(value = AccessLevel.NONE)
    private final List<OrderEvent> events = new ArrayList<>();

    public Order(UUID orderId, List<OrderItem> orderItems) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.orderStatus = OrderStatus.RECEIVED;
        this.orderItems = orderItems;
    }

    // Methods
    public void prepare() {
        this.orderStatus = OrderStatus.PREPARATION;
        this.startedAt = LocalDateTime.now();
        this.events.add(new OrderPreparationStarted(this.id, this.orderId));
    }

    public void complete(LocalDateTime completedAt) {
        this.orderStatus = OrderStatus.COMPLETE;
        this.completedAt = completedAt;
        this.events.add(new OrderPreparationCompleted(this.id, this.orderId));
    }

    // Event Methods
    public void clearEvents() {
        this.events.clear();
    }
    public List<OrderEvent> listEvents() {
        return events;
    }
}