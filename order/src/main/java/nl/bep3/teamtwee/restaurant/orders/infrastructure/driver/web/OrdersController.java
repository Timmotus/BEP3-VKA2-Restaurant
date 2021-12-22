package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web;

import java.util.List;
import java.util.UUID;

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

import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersQueryHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.RegisterOrder;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.GetOrderById;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.ListOrders;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request.RegisterOrderRequest;

/*
    Note that we are combining ideas of REST and CQRS
    in a task-based API.
    We are still resource-centric, but allow verb-like
    commands to be added as sub-resources using POST.

    See: https://codeopinion.com/is-a-rest-api-with-cqrs-possible/
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersCommandHandler commandHandler;
    private final OrdersQueryHandler queryHandler;

    public OrdersController(OrdersCommandHandler commandHandler, OrdersQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public Order registerOrder(@Valid @RequestBody RegisterOrderRequest request) {
        RegisterOrder order = new RegisterOrder(
                request.zipCode,
                request.street,
                request.streetNumber,
                request.itemCounts);
        return this.commandHandler.handle(order);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetOrderById(id));
    }

    @GetMapping
    public List<Order> findOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction) {
        return this.queryHandler.handle(new ListOrders(orderBy, direction));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleOrderNotFound(OrderNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
