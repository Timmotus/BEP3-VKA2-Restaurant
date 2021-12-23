package nl.teamtwee.bep3.restaurant.kitchen.core.application;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.OrderCompleted;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class KitchenTask {

    private final OrderRepository repository;
    private final OrdersCommandHandler commandHandler;
    private final long pollingRate = 10000;
    private final long deliverySpeedSeconds = 30;

    @Scheduled(fixedDelay = pollingRate)
    public void prepareOrder() {
        List<Order> orders = this.repository.findByStatus(OrderStatus.RECEIVED);
        orders.forEach(order -> {
            LocalDateTime receivedAt = order.getReceivedAt();
            LocalDateTime deliveryThreshold = receivedAt.plusSeconds(deliverySpeedSeconds);
            if (LocalDateTime.now().isAfter(deliveryThreshold)) {
                this.commandHandler.handle(new OrderCompleted(order.getId(), LocalDateTime.now()));
            }
        });
    }

}
