package nl.teamtwee.bep3.restaurant.menu.core.port.storage;

import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface PizzaRepository extends MongoRepository<Pizza, UUID> {
//    @Query("{ 'name' : ?0 }")
    Pizza findPizzaByName(String pizzaName);
    boolean existsByName(String name);
}
