package nl.teamtwee.bep3.restaurant.menu.core.port.storage;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PizzaRepository extends MongoRepository<Pizza, UUID> {
    Pizza findPizzaByName(String pizzaName);
}
