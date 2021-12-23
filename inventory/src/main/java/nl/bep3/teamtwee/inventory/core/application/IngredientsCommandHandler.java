package nl.bep3.teamtwee.inventory.core.application;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.inventory.core.application.command.*;
import nl.bep3.teamtwee.inventory.core.domain.event.IngredientEvent;
import nl.bep3.teamtwee.inventory.core.domain.exception.IngredientNotFound;
import nl.bep3.teamtwee.inventory.core.port.messaging.IngredientEventPublisher;
import nl.bep3.teamtwee.inventory.core.port.storage.IngredientRepository;
import nl.bep3.teamtwee.inventory.core.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        for (UUID uuid : command.getIngredientAmountMap().keySet())
            ingredients.add(this.repository.findById(uuid)
                    .orElseThrow(() -> new IngredientNotFound(uuid.toString())));

        for (Ingredient ingredient : ingredients) {
            for (UUID uuid : command.getIngredientAmountMap().keySet())
                ingredient.takeStock(command.getIngredientAmountMap().get(uuid));
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