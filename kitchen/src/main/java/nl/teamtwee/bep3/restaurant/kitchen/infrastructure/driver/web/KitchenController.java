package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.OrdersCommandHandler;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.OrdersQueryHandler;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.DeleteOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.ProceedWithOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.UpdateOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.command.UploadOrder;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.query.GetOrderById;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.query.ListOrders;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception.OrderNotFoundException;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception.OrderStatusException;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web.request.CreateOrderRequest;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web.request.UpdateOrderRequest;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web.response.OrderResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/kitchen")
public class KitchenController {
    private final OrdersCommandHandler commandHandler;
    private final OrdersQueryHandler queryHandler;

    @PostMapping
    public ResponseEntity<OrderResponse> uploadOrder(@RequestBody CreateOrderRequest orderItems) {
        return ResponseEntity.ok(new OrderResponse(
                this.commandHandler.handle(
                        new UploadOrder(orderItems.getOrderId(), orderItems.getItems()))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.queryHandler.handle(new GetOrderById(id)));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction) {
        return ResponseEntity.ok(this.queryHandler.handle(new ListOrders(orderBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderById(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok(
                this.commandHandler.handle(new UpdateOrder(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable UUID id) {
        this.commandHandler.handle(new DeleteOrder(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/proceed")
    public ResponseEntity<Order> proceedWithOrder(
            @PathVariable UUID id,
            @RequestParam(required = false) @Pattern(regexp = "RECEIVED|PREPARATION|BAKING|READY|COMPLETE") String orderStatus) {
        return ResponseEntity.ok(
                this.commandHandler.handle(new ProceedWithOrder(
                        id,
                        orderStatus == null ? null : OrderStatus.valueOf(orderStatus))));
    }

    // Handlers
    @ExceptionHandler
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleOrderStatusException(OrderStatusException exception) {
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