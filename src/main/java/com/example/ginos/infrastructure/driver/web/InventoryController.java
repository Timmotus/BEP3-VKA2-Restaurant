package com.example.ginos.infrastructure.driver.web;

import nl.bep3_teamtwee.inventory_service.core.application.ItemsCommandHandler;
import nl.bep3_teamtwee.inventory_service.core.application.ItemsQueryHandler;
import nl.bep3_teamtwee.inventory_service.core.application.command.RegisterItem;
import nl.bep3_teamtwee.inventory_service.core.application.query.FindItemsByProductName;
import nl.bep3_teamtwee.inventory_service.core.application.query.GetItemById;
import nl.bep3_teamtwee.inventory_service.core.application.query.ListItems;
import nl.bep3_teamtwee.inventory_service.core.domain.Item;
import nl.bep3_teamtwee.inventory_service.core.domain.Unit;
import nl.bep3_teamtwee.inventory_service.core.domain.exception.ItemNotFound;
import nl.bep3_teamtwee.inventory_service.infrastructure.driver.web.request.RegisterItemRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ItemsCommandHandler commandHandler;
    private final ItemsQueryHandler queryHandler;

    public InventoryController(ItemsCommandHandler commandHandler, ItemsQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public Item registerItem(@Valid @RequestBody RegisterItemRequest request) {
        return this.commandHandler.handle(new RegisterItem(request.productName, Unit.valueOf(request.unit), request.capacity,
                request.purchaseCapacity, request.sellCapacity, request.purchasePrice, request.sellPrice));
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetItemById(id));
    }

    @GetMapping(params = {"productName"})
    public List<Item> findItemsByProductName(
            @RequestParam String productName,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(
                new FindItemsByProductName(productName, orderBy, direction)
        );

    }

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) String orderBy,
                               @RequestParam(required = false) String direction) {
        return this.queryHandler.handle(new ListItems(orderBy, direction));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleItemNotFound(ItemNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
