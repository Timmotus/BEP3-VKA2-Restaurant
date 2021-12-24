package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web.response;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderItem;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;

@Getter
public class OrderResponse {
    private final UUID id;
    private final OrderStatus status;
    private final List<OrderItem> items;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.status = order.getOrderStatus();
        this.items = order.getOrderItems();
    }
}
