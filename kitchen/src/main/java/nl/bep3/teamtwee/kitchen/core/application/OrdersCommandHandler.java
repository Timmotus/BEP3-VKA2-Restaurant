package nl.bep3.teamtwee.kitchen.core.application;

import nl.bep3.teamtwee.kitchen.core.application.command.DeleteOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UpdateOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UploadOrder;
import nl.bep3.teamtwee.kitchen.core.domain.Order;
import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.kitchen.core.port.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.kitchen.core.port.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public OrdersCommandHandler(OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Order handle(UploadOrder command) {
        Order order = new Order();

        this.publishEventsFor(order);
        this.repository.save(order);

        return order;
    }

    public Order handle(UpdateOrder command) {
        Order order = this.repository.findById(command.getId())
                .orElseThrow(() -> new OrderNotFound(command.getId().toString()));

        this.publishEventsFor(order);
        this.repository.save(order);

        return order;
    }

    public void handle(DeleteOrder command) {
        Order order = this.repository.findById(command.getId())
                .orElseThrow(() -> new OrderNotFound(command.getId().toString()));

        this.publishEventsFor(order);
        this.repository.delete(order);
    }

    private void publishEventsFor(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();
    }
}
