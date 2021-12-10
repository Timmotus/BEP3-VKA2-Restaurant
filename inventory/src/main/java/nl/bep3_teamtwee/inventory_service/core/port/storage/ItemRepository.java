package nl.bep3_teamtwee.inventory_service.core.port.storage;

import nl.bep3_teamtwee.inventory_service.core.domain.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends MongoRepository<Item, UUID> {

    List<Item> findByProductNameEquals(String productName, Sort sort);
    List<Item> findByProductNameEquals(String productName);

}
