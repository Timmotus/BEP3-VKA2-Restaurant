package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging;

import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging.event.MenuItemEvent;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RabbitMqEventListener {
    private final MenuCommandHandler commandHandler;

    @RabbitListener(queues = "#{'${messaging.queue.menu}'}")
    void listen(MenuItemEvent event) {
        switch (event.getEventKey()) {
            case "event.key.here":
                break;
        }
    }
}
