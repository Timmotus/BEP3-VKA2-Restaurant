package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web;

import java.util.UUID;
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
import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersQueryHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.RegisterOrder;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.GetOrderById;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.ListOrders;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.OrderNotFoundException;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request.RegisterOrderRequest;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.response.OrderResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersCommandHandler commandHandler;
    private final OrdersQueryHandler queryHandler;

    @GetMapping
    public ResponseEntity<?> findOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction) {
        return ResponseEntity.ok(this.queryHandler
                .handle(new ListOrders(orderBy, direction)).stream()
                .map(order -> new OrderResponse(order))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(new OrderResponse(this.queryHandler.handle(new GetOrderById(id))));
    }

    @PostMapping
    public ResponseEntity<?> registerOrder(@Valid @RequestBody RegisterOrderRequest request) {
        RegisterOrder order = new RegisterOrder(
                request.zipCode,
                request.street,
                request.streetNumber,
                request.itemCounts);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(this.commandHandler.handle(order)));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleOrderNotFound(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
