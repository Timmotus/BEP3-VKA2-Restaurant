package nl.teamtwee.bep3.restaurant.menu.core.port.storage;

import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuItemRepository extends MongoRepository<MenuItem, UUID> {
    Boolean existsByName(String name);
    Optional<MenuItem> findByName(String name);
    List<MenuItem> findByNameIn(List<String> name);
}
