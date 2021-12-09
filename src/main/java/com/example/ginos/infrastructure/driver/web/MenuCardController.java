package com.example.ginos.infrastructure.driver.web;

import com.example.ginos.core.application.MenuCommandHandler;
import com.example.ginos.core.application.MenuQueryHandler;
import com.example.ginos.core.domain.Pizza;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("menu")
public class MenuCardController {

    private final MenuCommandHandler menuCommandHandler;
    private final MenuQueryHandler menuQueryHandler;

    public MenuCardController(MenuCommandHandler menuCommandHandler, MenuQueryHandler menuQueryHandler) {
        this.menuCommandHandler = menuCommandHandler;
        this.menuQueryHandler = menuQueryHandler;
    }

    @GetMapping("/all}")
    public Pizza getAlllPizza() {
        return this.menuQueryHandler.handle();
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
