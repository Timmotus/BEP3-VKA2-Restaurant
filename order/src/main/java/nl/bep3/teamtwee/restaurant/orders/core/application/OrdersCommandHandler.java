package nl.bep3.teamtwee.restaurant.orders.core.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.FailedOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.RegisterOrder;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order.OrderBuilder;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.OrderNotFoundException;
import nl.bep3.teamtwee.restaurant.orders.core.ports.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.KitchenRepository;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.MenuRepository;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.OrderRepository;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.PaymentRepository;

@AllArgsConstructor
@Service
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final MenuRepository menuGateway;
    private final KitchenRepository kitchenGateway;
    private final PaymentRepository paymentGateway;
    private final OrderEventPublisher eventPublisher;

    public Order handle(RegisterOrder command) {
        List<String> itemNames = command.getItemCounts()
                .keySet()
                .stream()
                .collect(Collectors.toList());
        List<MenuItem> menuItemsWithPrices = this.menuGateway.getMenuItemsByName(itemNames);

        OrderBuilder orderBuilder = Order.builder()
                .zipCode(command.getZipCode())
                .street(command.getStreet())
                .streetNumber(command.getStreetNumber());
        // Merge menuItems and orderItems into order
        menuItemsWithPrices.forEach(item -> orderBuilder.addItem(item.getName(),
                item.getPrice(),
                command.getItemCounts().get(item.getName())));

        // Reserve items with the kitchen, maybe want this to be asynchronous
        // UUID reservationId = this.kitchenGateway.createReservation(orderBuilder.getId(), itemNames);

        // Request a payment
        // UUID paymentId = this.paymentGateway.createPayment(
        //         orderBuilder.getId(),
        //         menuItemsWithPrices.stream().mapToLong(item -> item.getPrice()).sum());

        Order order = orderBuilder
                .paymentId(UUID.randomUUID())
                .reservationId(UUID.randomUUID())
                .status("PAYMENT_REQUIRED")
                .build();

        order.completePayment();
        // maybe throw event that an order is created

        this.publishEvents(order);
        this.repository.save(order);
        return order;
    }

    public void handle(CompleteOrderPayment command) {
        Order order = findOrderById(command.getOrderId());
        order.completePayment();
        this.publishEvents(order);
        this.repository.save(order);
    }

    public void handle(FailedOrderPayment command) {
        Order order = findOrderById(command.getOrderId());
        // TODO: process order failure
        this.repository.save(order);
    }

    private Order findOrderById(UUID id) {
        // should maybe log instead of throw error for our current use cases
        return this.repository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Order with id '{}' not found.", id)));
    }

    private void publishEvents(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publishSend);
        order.clearEvents();
    }
}
