package nl.bep3.teamtwee.kitchen.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderStatusException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private List<OrderItem> orderItems;

    @Transient
    @Setter(value = AccessLevel.NONE)
    private final List<OrderEvent> events = new ArrayList<>();

    public Order(List<OrderItem> orderItems) {
        this.id = UUID.randomUUID();
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
}