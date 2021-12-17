package nl.bep3.teamtwee.inventory.core.application;

import nl.bep3.teamtwee.inventory.core.application.command.BuyStockForIngredientWithId;
import nl.bep3.teamtwee.inventory.core.application.command.DeleteIngredient;
import nl.bep3.teamtwee.inventory.core.application.command.RegisterIngredient;
import nl.bep3.teamtwee.inventory.core.application.command.UpdateIngredient;
import nl.bep3.teamtwee.inventory.core.domain.event.IngredientEvent;
import nl.bep3.teamtwee.inventory.core.domain.exception.IngredientNotFound;
import nl.bep3.teamtwee.inventory.core.port.messaging.IngredientEventPublisher;
import nl.bep3.teamtwee.inventory.core.port.storage.IngredientRepository;
import nl.bep3.teamtwee.inventory.core.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsCommandHandler {

    private final IngredientRepository repository;
    private final IngredientEventPublisher eventPublisher;

    public IngredientsCommandHandler(IngredientRepository repository, IngredientEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

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

    private void publishEventsFor(Ingredient ingredient) {
        List<IngredientEvent> events = ingredient.listEvents();
        events.forEach(eventPublisher::publish);
        ingredient.clearEvents();
    }

}
