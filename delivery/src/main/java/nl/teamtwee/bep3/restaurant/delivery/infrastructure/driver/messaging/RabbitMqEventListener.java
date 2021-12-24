package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging;

import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryCommandHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.command.CreateDelivery;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    @RabbitListener(queues = "#{'${messaging.queue.delivery}'}")
    void listen(OrderDeliveryEvent event) throws InterruptedException {
        switch (event.eventKey) {
            case "delivery.order.create":
                this.commandHandler.handle(new CreateDelivery(event.orderId));
                break;
        }
    }
}
