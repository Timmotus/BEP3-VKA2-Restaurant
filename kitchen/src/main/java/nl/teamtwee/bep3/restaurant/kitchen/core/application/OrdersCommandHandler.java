package nl.teamtwee.bep3.restaurant.kitchen.core.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.DeleteOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.ProceedWithOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.UpdateOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.UploadOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderItem;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.event.OrderEvent;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception.OrderNotFoundException;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.messaging.OrderEventPublisher;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.InventoryRepository;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.MenuRepository;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.OrderRepository;

@Service
@AllArgsConstructor
public class OrdersCommandHandler {
    private final OrderRepository repository;
    private final InventoryRepository inventoryGateway;
    private final MenuRepository menuGateway;
    private final OrderEventPublisher eventPublisher;

    public Order handle(UploadOrder command) {
        // get ingredients for each item from menu
        List<MenuItem> menuItems = this.menuGateway.getMenuItemsByNames(new ArrayList<>(command.getItems().keySet()));
        List<OrderItem> orderItems = menuItems.stream()
                .map(item -> new OrderItem(
                        item.getName(),
                        command.getItems().get(item.getName()),
                        item.getIngredients()))
                .collect(Collectors.toList());
        Order order = new Order(orderItems);

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

        if (command.getOrderStatus() == null)
            order.proceed();
        else
            order.proceedTo(command.getOrderStatus());

        // Update Stock if OrderStatus is on complete
        if (order.getOrderStatus() == OrderStatus.COMPLETE) {
            Map<String, Long> ingredientAmountMap = new HashMap<>();

            for (OrderItem item : order.getOrderItems()) {
                for (String name : item.getIngredients().keySet()) {
                    Long n = item.getIngredients().get(name);

                    if (ingredientAmountMap.containsKey(name)) {
                        Long o = ingredientAmountMap.get(name);
                        ingredientAmountMap.replace(name, o + n);
                    } else
                        ingredientAmountMap.put(name, n);
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