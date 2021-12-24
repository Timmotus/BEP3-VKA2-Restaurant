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
    private final long preparationSpeedSeconds = 30;

    @Scheduled(fixedDelay = pollingRate)
    public void prepareOrder() {
        List<Order> orders = this.repository.findByOrderStatus(OrderStatus.PREPARATION);
        orders.forEach(order -> {
            LocalDateTime startedAt = order.getStartedAt();
            LocalDateTime finishThreshold = startedAt.plusSeconds(preparationSpeedSeconds);
            if (LocalDateTime.now().isAfter(finishThreshold)) {
                this.commandHandler.handle(new OrderCompleted(order.getId(), LocalDateTime.now()));
            }
        });
    }

}
