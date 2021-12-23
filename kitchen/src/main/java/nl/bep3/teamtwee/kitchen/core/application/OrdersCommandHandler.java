package nl.bep3.teamtwee.kitchen.core.application;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.kitchen.core.application.command.DeleteOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.ProceedWithOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UpdateOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UploadOrder;
import nl.bep3.teamtwee.kitchen.core.domain.Order;
import nl.bep3.teamtwee.kitchen.core.domain.OrderItem;
import nl.bep3.teamtwee.kitchen.core.domain.OrderStatus;
import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderNotFoundException;
import nl.bep3.teamtwee.kitchen.core.port.messaging.OrderEventPublisher;
import nl.bep3.teamtwee.kitchen.core.port.storage.InventoryRepository;
import nl.bep3.teamtwee.kitchen.core.port.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final InventoryRepository inventoryGateway;
    private final OrderEventPublisher eventPublisher;

    public Order handle(UploadOrder command) {
        Order order = new Order(command.getOrderItems());

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

        // Update Stock if OrderStatus is on complete
        if (order.getOrderStatus() == OrderStatus.COMPLETE) {
            Map<UUID, Integer> ingredientAmountMap = new HashMap<>();

            for (OrderItem item : order.getOrderItems()) {
                for (UUID uuid : item.getIngredients().keySet()) {
                    int n = item.getIngredients().get(uuid);

                    if (ingredientAmountMap.containsKey(uuid)) {
                        int o = ingredientAmountMap.get(uuid);
                        ingredientAmountMap.replace(uuid, o + n);
                    }
                    else ingredientAmountMap.put(uuid, n);
                }
            }

            inventoryGateway.removeStock(ingredientAmountMap);
        }

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