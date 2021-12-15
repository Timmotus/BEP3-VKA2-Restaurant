package nl.bep3.teamtwee.restaurant.orders.core.application;

import org.springframework.stereotype.Service;

import nl.bep3.teamtwee.restaurant.orders.core.application.command.*;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.restaurant.orders.core.ports.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.OrderRepository;

import java.util.List;

@Service
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public OrdersCommandHandler(OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Order handle(RegisterOrder command) {
        // check if command.getContents() ingredient are available
        Order order = new Order(
            command.getZipCode(),
            command.getStreet(),
            command.getStreetNumber(),
            command.getStatus(),
            command.getContents()
        );

        this.publishEventsAndSave(order);

        return order;
    }

    public Order handle(CreatedOrderPayment command) {
        // set paymentid on order
        return new Order();
    }

    public Order handle(CompleteOrderPayment command) {
        // complete order and send to kitchen
        return new Order();
    }

    public Order handle(InvalidateOrderPayment command) {
        // invalidate order
        return new Order();
    }

    private void publishEventsAndSave(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();

        this.repository.save(order);
    }
}
