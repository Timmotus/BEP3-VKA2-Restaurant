package nl.teamtwee.bep3.restaurant.kitchen.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.OrderCompleted;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderEvent;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception.OrderStatusException;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Order {
    @Id
    private UUID id;

    private OrderStatus orderStatus;
    private LocalDateTime receivedAt;
    private LocalDateTime completedAt;
    private List<OrderItem> orderItems;

    @Transient
    @Setter(value = AccessLevel.NONE)
    private final List<OrderEvent> events = new ArrayList<>();

    public Order(List<OrderItem> orderItems) {
        this.id = UUID.randomUUID();
        this.receivedAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.RECEIVED;
        this.orderItems = orderItems;
    }

    // Methods
    public void proceed() {
        if (this.orderStatus == OrderStatus.COMPLETE) throw new OrderStatusException("This order is finished and therefore cannot proceed!");
        this.orderStatus = this.orderStatus.next();
    }

    public void proceedTo(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Event Methods
    public void clearEvents() {
        this.events.clear();
    }
    public List<OrderEvent> listEvents() {
        return events;
    }

    public void prepare(LocalDateTime completedAt) {
        this.orderStatus = OrderStatus.COMPLETE;
        this.completedAt = completedAt;
    }
}