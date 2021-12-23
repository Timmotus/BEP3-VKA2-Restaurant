package nl.bep3.teamtwee.restaurant.inventory.infrastructure.driver.web;

import nl.bep3.teamtwee.restaurant.inventory.core.application.command.*;
import nl.bep3.teamtwee.restaurant.inventory.core.application.command.*;
import nl.bep3.teamtwee.restaurant.inventory.infrastructure.driver.web.request.RegisterIngredientRequest;
import nl.bep3.teamtwee.restaurant.inventory.infrastructure.driver.web.request.ReserveIngredientRequest;
import nl.bep3.teamtwee.restaurant.inventory.infrastructure.driver.web.request.UpdateIngredientRequest;
import nl.bep3.teamtwee.restaurant.inventory.core.application.IngredientsCommandHandler;
import nl.bep3.teamtwee.restaurant.inventory.core.application.IngredientsQueryHandler;
import nl.bep3.teamtwee.restaurant.inventory.core.application.query.GetIngredientById;
import nl.bep3.teamtwee.restaurant.inventory.core.application.query.FindIngredientsByProductName;
import nl.bep3.teamtwee.restaurant.inventory.core.application.query.ListIngredients;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.Ingredient;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.Unit;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.exception.InsufficientStockCapacity;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.exception.IngredientNotFound;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final IngredientsCommandHandler commandHandler;
    private final IngredientsQueryHandler queryHandler;

    public InventoryController(IngredientsCommandHandler commandHandler, IngredientsQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public ResponseEntity<Ingredient> registerIngredient(@Valid @RequestBody RegisterIngredientRequest request) {
        return ResponseEntity.ok(
                this.commandHandler.handle(
                        new RegisterIngredient(
                            request.productName,
                            Unit.valueOf(request.unit),
                            request.capacity,
                            request.purchaseCapacity,
                            request.sellCapacity,
                            request.purchasePrice,
                            request.sellPrice
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.queryHandler.handle(new GetIngredientById(id)));
    }

    @Validated
    @GetMapping(params = {"productName"})
    public ResponseEntity<List<Ingredient>> findIngredientsByProductName(
            @RequestParam String productName,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) @Pattern(regexp = "asc|desc") String direction
    ) {
        return ResponseEntity.ok(this.queryHandler.handle(new FindIngredientsByProductName(productName, orderBy, direction)));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return ResponseEntity.ok(this.queryHandler.handle(new ListIngredients(orderBy, direction)));
    }

    @PostMapping("/{id}/stock")
    public ResponseEntity<Ingredient> buyStockForIngredientWithId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.commandHandler.handle(new BuyStockForIngredientWithId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredientById(@PathVariable UUID id, @Valid @RequestBody UpdateIngredientRequest request) {
        return ResponseEntity.ok(
                this.commandHandler.handle(
                        new UpdateIngredient(
                                id,
                                request.productName,
                                Unit.valueOf(request.unit),
                                request.stock,
                                request.capacity,
                                request.purchaseCapacity,
                                request.sellCapacity,
                                request.purchasePrice,
                                request.sellPrice
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientById(@PathVariable UUID id) {
        this.commandHandler.handle(new DeleteIngredient(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveIngredientGroup(@Valid @RequestBody List<ReserveIngredientRequest> ingredientList) {
        // this.commandHandler.handle(new ReserveIngredients(ingredientList));
        return ResponseEntity.noContent().build();
    }

    // Handlers
    @ExceptionHandler
    public ResponseEntity<String> handleIngredientNotFound(IngredientNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInsufficientStockCapacity(InsufficientStockCapacity exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
