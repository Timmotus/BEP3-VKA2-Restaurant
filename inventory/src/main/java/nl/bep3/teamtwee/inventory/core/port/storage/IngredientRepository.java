package nl.bep3.teamtwee.inventory.core.port.storage;

import nl.bep3.teamtwee.inventory.core.domain.Ingredient;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {

    List<Ingredient> findByProductNameEquals(String productName, Sort sort);
    List<Ingredient> findByProductNameEquals(String productName);

}
