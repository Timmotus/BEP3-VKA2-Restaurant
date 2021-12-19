package nl.bep3.teamtwee.restaurant.inventory.core.application;

import nl.bep3.teamtwee.restaurant.inventory.core.application.query.FindIngredientsByProductName;
import nl.bep3.teamtwee.restaurant.inventory.core.application.query.GetIngredientById;
import nl.bep3.teamtwee.restaurant.inventory.core.application.query.ListIngredients;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.exception.IngredientNotFound;
import nl.bep3.teamtwee.restaurant.inventory.core.port.storage.IngredientRepository;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.Ingredient;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsQueryHandler {

    private final IngredientRepository repository;

    public IngredientsQueryHandler(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient handle(GetIngredientById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new IngredientNotFound(query.getId().toString()));
    }

    public List<Ingredient> handle(FindIngredientsByProductName query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByProductNameEquals(query.getProductName(), sort);
    }

    public List<Ingredient> handle(ListIngredients query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        return Sort.by(Sort.Direction.fromString(direction), orderBy);
    }

}
