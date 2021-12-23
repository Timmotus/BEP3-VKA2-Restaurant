package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging;

import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryCommandHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.command.CreateDelivery;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging.event.DeliveryKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.delivery}'}")
    void listen(DeliveryKeywordEvent event) throws InterruptedException {
        switch (event.eventKey) {
            case "delivery.order.create":
                this.commandHandler.handle(new CreateDelivery(event.orderId));
                break;
        }
    }
}
