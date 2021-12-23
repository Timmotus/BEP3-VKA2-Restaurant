package nl.teamtwee.bep3.restaurant.inventory.core.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.inventory.core.application.command.BuyStockForIngredientWithId;
import nl.teamtwee.bep3.restaurant.inventory.core.application.command.DeleteIngredient;
import nl.teamtwee.bep3.restaurant.inventory.core.application.command.RegisterIngredient;
import nl.teamtwee.bep3.restaurant.inventory.core.application.command.TakeFromStock;
import nl.teamtwee.bep3.restaurant.inventory.core.application.command.UpdateIngredient;
import nl.teamtwee.bep3.restaurant.inventory.core.domain.Ingredient;
import nl.teamtwee.bep3.restaurant.inventory.core.domain.event.IngredientEvent;
import nl.teamtwee.bep3.restaurant.inventory.core.domain.exception.IngredientNotFound;
import nl.teamtwee.bep3.restaurant.inventory.core.port.messaging.IngredientEventPublisher;
import nl.teamtwee.bep3.restaurant.inventory.core.port.storage.IngredientRepository;

@Service
@AllArgsConstructor
public class IngredientsCommandHandler {
    private final IngredientRepository repository;
    private final IngredientEventPublisher eventPublisher;

    public Ingredient handle(RegisterIngredient command) {
        Ingredient ingredient = new Ingredient(
                command.getProductName(),
                command.getUnit(),
                command.getCapacity(),
                command.getPurchaseCapacity(),
                command.getSellCapacity(),
                command.getPurchasePrice(),
                command.getSellPrice()
        );

        this.publishEventsFor(ingredient);
        this.repository.save(ingredient);

        return ingredient;
    }

    public Ingredient handle(UpdateIngredient command) {
        Ingredient ingredient = this.repository.findById(command.getId())
                .orElseThrow(() -> new IngredientNotFound(command.getId().toString()));

        ingredient.setProductName(command.getProductName());
        ingredient.setStock(command.getStock());
        ingredient.setUnit(command.getUnit());
        ingredient.setCapacity(command.getCapacity());
        ingredient.setPurchasePrice(command.getPurchasePrice());
        ingredient.setPurchaseCapacity(command.getPurchaseCapacity());
        ingredient.setSellPrice(command.getSellPrice());
        ingredient.setSellCapacity(command.getSellCapacity());

        this.publishEventsFor(ingredient);
        this.repository.save(ingredient);

        return ingredient;
    }

    public Ingredient handle(BuyStockForIngredientWithId command) {
        Ingredient ingredient = this.repository.findById(command.getId())
                .orElseThrow(() -> new IngredientNotFound(command.getId().toString()));

        ingredient.buyStock();

        this.publishEventsFor(ingredient);
        this.repository.save(ingredient);

        return ingredient;
    }

    public void handle(DeleteIngredient command) {
        Ingredient ingredient = this.repository.findById(command.getId())
                .orElseThrow(() -> new IngredientNotFound(command.getId().toString()));

        this.publishEventsFor(ingredient);
        this.repository.delete(ingredient);
    }


    public List<Ingredient> handle(TakeFromStock command) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (String name : command.getIngredientAmountMap().keySet())
            ingredients.add(this.repository.findByProductName(name)
                    .orElseThrow(() -> new IngredientNotFound(name)));

        for (Ingredient ingredient : ingredients) {
            for (String name : command.getIngredientAmountMap().keySet())
                ingredient.takeStock(command.getIngredientAmountMap().get(name));
        }

        for (Ingredient ingredient : ingredients) {
            this.publishEventsFor(ingredient);
            this.repository.save(ingredient);
        }

        return ingredients;
    }

    private void publishEventsFor(Ingredient ingredient) {
        List<IngredientEvent> events = ingredient.listEvents();
        events.forEach(eventPublisher::publish);
        ingredient.clearEvents();
    }

}