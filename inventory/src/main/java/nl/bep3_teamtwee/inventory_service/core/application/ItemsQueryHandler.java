package nl.bep3_teamtwee.inventory_service.core.application;

import nl.bep3_teamtwee.inventory_service.core.application.query.FindItemsByProductName;
import nl.bep3_teamtwee.inventory_service.core.application.query.GetItemById;
import nl.bep3_teamtwee.inventory_service.core.application.query.ListItems;
import nl.bep3_teamtwee.inventory_service.core.domain.Item;
import nl.bep3_teamtwee.inventory_service.core.domain.exception.ItemNotFound;
import nl.bep3_teamtwee.inventory_service.core.port.storage.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsQueryHandler {

    private final ItemRepository repository;

    public ItemsQueryHandler(ItemRepository repository) {
        this.repository = repository;
    }

    public Item handle(GetItemById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new ItemNotFound(query.getId().toString()));
    }

    public List<Item> handle(FindItemsByProductName query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByProductNameEquals(query.getProductName(), sort);
    }

    public List<Item> handle(ListItems query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }

}
