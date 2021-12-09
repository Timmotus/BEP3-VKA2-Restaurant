package com.example.ginos.core.application;

import com.example.ginos.core.application.command.RegisterItem;
import com.example.ginos.core.domain.event.MenuEvent;
import com.example.ginos.core.port.messaging.MenuEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCommandHandler {

    private final ItemRepository repository;
    private final MenuEventPublisher eventPublisher;

    public MenuCommandHandler(ItemRepository repository, MenuEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Item handle(RegisterItem command) {
        Item item = new Item(command.getProductName(), command.getUnit(), command.getCapacity(),
                command.getPurchaseCapacity(), command.getSellCapacity(), command.getPurchasePrice(),
                command.getSellPrice());

        this.publishEventsFor(item);
        this.repository.save(item);

        return item;
    }

    private void publishEventsFor(Item item) {
        List<MenuEvent> events = item.listEvents();
        events.forEach(eventPublisher::publish);
        item.clearEvents();
    }

}
