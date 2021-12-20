package com.example.ginos.core.port.messaging;

import com.example.ginos.core.domain.event.MenuEvent;

public interface MenuEventPublisher {
    void publish(MenuEvent event);
}
