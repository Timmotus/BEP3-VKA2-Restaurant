package nl.bep3.teamtwee.kitchen.core.application;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.kitchen.core.application.command.DeleteOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.ProceedWithOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UpdateOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UploadOrder;
import nl.bep3.teamtwee.kitchen.core.domain.Order;
import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderNotFoundException;
import nl.bep3.teamtwee.kitchen.core.port.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.kitchen.core.port.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public Order handle(UploadOrder command) {
        Order order = new Order();

        this.publishEventsFor(order);
        this.repository.save(order);

        return order;
    }

    public Order handle(UpdateOrder command) {
        Order order = this.repository.findById(command.getId())
                .orElseThrow(() -> new OrderNotFoundException(command.getId().toString()));

        this.publishEventsFor(order);
        this.repository.save(order);

        return order;
    }

    public void handle(DeleteOrder command) {
        Order order = this.repository.findById(command.getId())
                .orElseThrow(() -> new OrderNotFoundException(command.getId().toString()));

        this.publishEventsFor(order);
        this.repository.delete(order);
    }

    public Order handle(ProceedWithOrder command) {
        Order order = this.repository.findById(command.getId())
                .orElseThrow(() -> new OrderNotFoundException(command.getId().toString()));

        if (command.getOrderStatus() == null) order.proceed();
        else order.proceedTo(command.getOrderStatus());

        this.publishEventsFor(order);
        this.repository.save(order);

        return order;
    }

    private void publishEventsFor(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();
    }
}