package nl.teamtwee.bep3.restaurant.inventory.core.port.storage;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.inventory.core.domain.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {
    List<Ingredient> findByProductNameEquals(String productName, Sort sort);
    List<Ingredient> findByProductNameEquals(String productName);
}