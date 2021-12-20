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
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderMenuItemAvailableEvent;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderReserveIngredientEvent;
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

        OrderBuilder orderBuilder = Order.builder()
                .zipCode(command.getZipCode())
                .street(command.getStreet())
                .streetNumber(command.getStreetNumber())
                .paymentId(UUID.randomUUID()) // should be gotten from the payment request
                .status("PAYMENT_REQUIRED");

        Object ret = this.eventPublisher
                .publishSendAndReceive(new OrderMenuItemAvailableEvent(itemNames));
        if (ret != null) {
            System.out.println(ret);
        } else {
            System.out.println("no response from wherever");
        }
        // TODO: try to reserve ingredients at inventoryservice
        // Map<String, Integer> ingredients = Map.of(
        // "Test1", 100,
        // "Test2", 200,
        // "Test3", 300);
        // Object ret = this.eventPublisher.publishSendAndReceive(new
        // OrderReserveIngredientEvent(orderBuilder.getId(), ingredients));
        // if (ret != null) {
        // System.out.println(ret);
        // } else {
        // System.out.println("no response from wherever");
        // }

        // TODO: request payment

        // TODO: map ingredients and options from result to items
        command.getItems().forEach(item -> {
            Map<String, Integer> items = new HashMap<>();
            itemNames.forEach(itemName -> items.put(itemName, 100));
            orderBuilder.addItem(new OrderItem(item.getName(), item.getCount(), items, item.getOptions()));
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
        events.forEach(eventPublisher::publishSend);
        order.clearEvents();
    }
}
