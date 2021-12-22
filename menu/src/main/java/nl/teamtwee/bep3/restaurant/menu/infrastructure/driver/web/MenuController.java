package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.MenuQueryHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.RegisterMenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetAllMenuItems;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetMenuItemByName;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPricesByNames;
import nl.teamtwee.bep3.restaurant.menu.core.domain.exception.MenuItemNotFoundException;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.RegisterMenuItemRequest;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.response.ErrorResponse;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.response.MenuItemResponse;

@AllArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {
    private final MenuCommandHandler menuCommandHandler;
    private final MenuQueryHandler menuQueryHandler;

    @GetMapping
    public ResponseEntity<?> getAllMenuItems() {
        return ResponseEntity.ok(
                this.menuQueryHandler.handle(new GetAllMenuItems())
                        .stream()
                        .map(item -> new MenuItemResponse(item))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/items/{name}")
    public ResponseEntity<?> getMenuItemByName(@PathVariable String name) {
        return ResponseEntity.ok(new MenuItemResponse(this.menuQueryHandler.handle(new GetMenuItemByName(name))));
    }

    @GetMapping("/prices")
    public ResponseEntity<?> getPricesByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(this.menuCommandHandler.handle(new GetPricesByNames(names)));
    }

    @PostMapping("/items")
    public ResponseEntity<?> registerMenuItem(@Valid @RequestBody RegisterMenuItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MenuItemResponse(this.menuCommandHandler
                        .handle(new RegisterMenuItem(request.name, request.ingredients, request.price))));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMenuItemNotFound(MenuItemNotFoundException exception) {
        ErrorResponse response = new ErrorResponse("NotFound", exception.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleDuplicate(DuplicateKeyException exception) {
        ErrorResponse response = new ErrorResponse("Duplicate", "The item already exists.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
