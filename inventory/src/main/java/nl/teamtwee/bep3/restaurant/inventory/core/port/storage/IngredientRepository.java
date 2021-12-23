package nl.teamtwee.bep3.restaurant.inventory.core.port.storage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.inventory.core.domain.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {
    List<Ingredient> findByProductNameEquals(String productName, Sort sort);
    Optional<Ingredient> findByProductName(String productName);
}