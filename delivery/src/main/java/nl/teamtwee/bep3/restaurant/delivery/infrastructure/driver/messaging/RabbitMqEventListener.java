package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging;

import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryCommandHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.command.DeliveryStatus;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging.event.DeliveryKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.menu}'}")
    void listen(DeliveryKeywordEvent event) {
        switch (event.eventKey) {
            case "keywords.delivery.status":
                this.commandHandler.handle(new DeliveryStatus(event.deliveryStatusEnum));
                break;
        }
    }
}
