package nl.bep3.teamtwee.restaurant.orders.core.application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.FailedOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.RegisterOrder;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order.OrderBuilder;
import nl.bep3.teamtwee.restaurant.orders.core.domain.OrderItem;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.restaurant.orders.core.ports.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.OrderRepository;

@Service
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public OrdersCommandHandler(OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    // discuss availability over consistency
    public Order handle(RegisterOrder command) {
        Set<String> itemNames = new HashSet<>();
        command.getItems().forEach(item -> itemNames.add(item.getName()));
        // TODO: get all itemNames from menuservice in one request, availability check
        // TODO: validate if options are applicable to item

        // TODO: try to reserve ingredients at inventoryservice

        // TODO: request payment

        OrderBuilder orderBuilder = Order.builder()
                .zipCode(command.getZipCode())
                .street(command.getStreet())
                .streetNumber(command.getStreetNumber())
                .paymentId(UUID.randomUUID()) // should be gotten from the payment request
                .status("PAYMENT_REQUIRED");

        // TODO: map ingredients and options from result to items
        command.getItems().forEach(item -> {
            Map<String, Integer> ingredients = new HashMap<>();
            itemNames.forEach(itemName -> ingredients.put(itemName, 100));
            orderBuilder.addItem(new OrderItem(item.getName(), item.getCount(), ingredients, item.getOptions()));
        });

        Order order = orderBuilder.build();

        // maybe throw event that an order is created

        this.publishEvents(order);
        this.repository.save(order);
        return order;
    }

    public void handle(CompleteOrderPayment command) {
        Order order = findOrderById(command.getOrderId());
        order.setStatus("PAYMENT_COMPLETE");
        this.repository.save(order);
    }

    public void handle(FailedOrderPayment command) {
        Order order = findOrderById(command.getOrderId());
        order.setStatus("PAYMENT_FAILED");
        this.repository.save(order);
    }

    private Order findOrderById(UUID id) {
        // should maybe log instead of throw error for our current use cases
        return this.repository
                .findById(id)
                .orElseThrow(() -> new OrderNotFound(
                        String.format("Order with id '{}' not found.", id)));
    }

    private void publishEvents(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();
    }
}
