package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.OrdersCommandHandler;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.PrepareOrder;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.messaging.event.KitchenOrderEvent;

@AllArgsConstructor
@Component
public class RabbitMqEventListener {
    private final OrdersCommandHandler commandHandler;

    @RabbitListener(queues = "#{'${messaging.queue.kitchen-orders}'}")
    void listen(KitchenOrderEvent event) {
        switch (event.getEventKey()) {
            case "kitchen.order.prepare":
                this.commandHandler.handle(
                        new PrepareOrder(event.getReservationId(), event.getOrderId()));
                break;
        }
    }
}
