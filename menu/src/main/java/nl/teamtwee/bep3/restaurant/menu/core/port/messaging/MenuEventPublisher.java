package nl.teamtwee.bep3.restaurant.menu.core.port.messaging;

import nl.teamtwee.bep3.restaurant.menu.core.domain.event.MenuEvent;

public interface MenuEventPublisher {
    void publish(MenuEvent event);
}
