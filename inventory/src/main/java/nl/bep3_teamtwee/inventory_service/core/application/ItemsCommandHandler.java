package nl.bep3_teamtwee.inventory_service.core.application;

import nl.bep3_teamtwee.inventory_service.core.application.command.BuyStockForItemWithId;
import nl.bep3_teamtwee.inventory_service.core.application.command.DeleteItem;
import nl.bep3_teamtwee.inventory_service.core.application.command.RegisterItem;
import nl.bep3_teamtwee.inventory_service.core.application.command.UpdateItem;
import nl.bep3_teamtwee.inventory_service.core.domain.Item;
import nl.bep3_teamtwee.inventory_service.core.domain.event.ItemEvent;
import nl.bep3_teamtwee.inventory_service.core.domain.exception.ItemNotFound;
import nl.bep3_teamtwee.inventory_service.core.port.messaging.ItemEventPublisher;
import nl.bep3_teamtwee.inventory_service.core.port.storage.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsCommandHandler {

    private final ItemRepository repository;
    private final ItemEventPublisher eventPublisher;

    public ItemsCommandHandler(ItemRepository repository, ItemEventPublisher eventPublisher) {
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

    public Item handle(UpdateItem command) {
        Item item = this.repository.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound(command.getId().toString()));

        item.setProductName(command.getProductName());
        item.setStock(command.getStock());
        item.setUnit(command.getUnit());
        item.setCapacity(command.getCapacity());
        item.setPurchasePrice(command.getPurchasePrice());
        item.setPurchaseCapacity(command.getPurchaseCapacity());
        item.setSellPrice(command.getSellPrice());
        item.setSellCapacity(command.getSellCapacity());

        this.publishEventsFor(item);
        this.repository.save(item);

        return item;
    }

    public Item handle(BuyStockForItemWithId command) {
        Item item = this.repository.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound(command.getId().toString()));
        item.buyStock();

        this.publishEventsFor(item);
        this.repository.save(item);

        return item;
    }

    public void handle(DeleteItem command) {
        Item item = this.repository.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound(command.getId().toString()));

        this.publishEventsFor(item);
        this.repository.delete(item);
    }

    private void publishEventsFor(Item item) {
        List<ItemEvent> events = item.listEvents();
        events.forEach(eventPublisher::publish);
        item.clearEvents();
    }

}
