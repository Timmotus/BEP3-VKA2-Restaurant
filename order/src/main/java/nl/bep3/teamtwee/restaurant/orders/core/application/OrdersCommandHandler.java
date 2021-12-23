package nl.bep3.teamtwee.restaurant.orders.core.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.FailedOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryDelivered;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryStarted;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.RegisterOrder;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.GetOrderById;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order.OrderBuilder;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
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
    private final OrdersQueryHandler queryHandler;

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
        UUID paymentId = this.paymentGateway.createPayment(
                orderBuilder.getId(),
                menuItemsWithPrices.stream().mapToDouble(item -> item.getPrice()).sum());

        Order order = orderBuilder
                .paymentId(paymentId)
                .reservationId(UUID.randomUUID())
                .build();
        // maybe throw event that an order is created

        this.publishEventsAndSave(order);
        return order;
    }

    public void handle(CompleteOrderPayment command) {
        Order order = queryHandler.handle(new GetOrderById(command.getOrderId()));
        order.completePayment();
        this.publishEventsAndSave(order);
    }

    public void handle(FailedOrderPayment command) {
        Order order = queryHandler.handle(new GetOrderById(command.getOrderId()));
        // TODO: process order failure
        this.repository.save(order);
    }

    public void handle(OrderDeliveryStarted command) {
        Order order = queryHandler.handle(new GetOrderById(command.getOrderId()));
        order.deliveryStarted();
        this.repository.save(order);
    }

    public void handle(OrderDeliveryDelivered command) {
        Order order = queryHandler.handle(new GetOrderById(command.getOrderId()));
        order.deliveryDelivered();
        this.repository.save(order);
    }

    private void publishEventsAndSave(Order order) {
        List<OrderEvent> events = new ArrayList<>(order.listEvents());
        order.clearEvents();
        this.repository.save(order);
        events.forEach(eventPublisher::publishSend);
    }
}
