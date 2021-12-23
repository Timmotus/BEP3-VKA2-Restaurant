package nl.bep3.teamtwee.kitchen.infrastructure.driver.web;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.kitchen.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.kitchen.core.application.OrdersQueryHandler;
import nl.bep3.teamtwee.kitchen.core.application.command.DeleteOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.ProceedWithOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UpdateOrder;
import nl.bep3.teamtwee.kitchen.core.application.command.UploadOrder;
import nl.bep3.teamtwee.kitchen.core.application.query.GetOrderById;
import nl.bep3.teamtwee.kitchen.core.application.query.ListOrders;
import nl.bep3.teamtwee.kitchen.core.domain.Order;
import nl.bep3.teamtwee.kitchen.core.domain.OrderStatus;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.kitchen.infrastructure.driver.web.request.UpdateOrderRequest;
import nl.bep3.teamtwee.kitchen.infrastructure.driver.web.request.UploadOrderRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/kitchen")
public class KitchenController {
    private final OrdersCommandHandler commandHandler;
    private final OrdersQueryHandler queryHandler;

    @PostMapping
    public ResponseEntity<Order> uploadOrder(@Valid @RequestBody UploadOrderRequest request) {
        return ResponseEntity.ok(
                this.commandHandler.handle(
                        new UploadOrder(

                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.queryHandler.handle(new GetOrderById(id)));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return ResponseEntity.ok(this.queryHandler.handle(new ListOrders(orderBy, direction)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable UUID id, @Valid @RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok(
                this.commandHandler.handle(
                        new UpdateOrder(
                                id
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable UUID id) {
        this.commandHandler.handle(new DeleteOrder(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/proceed")
    public ResponseEntity<Order> proceedWithOrder(
            @PathVariable UUID id,
            @RequestParam(required = false) @Pattern(regexp = "RECEIVED|PREPARATION|BAKING|READY|COMPLETE") String orderStatus
    ) {
        return ResponseEntity.ok(
                this.commandHandler.handle(
                        new ProceedWithOrder(
                                id,
                                OrderStatus.valueOf(orderStatus)
                        )
                )
        );
    }

    // Handlers
    @ExceptionHandler
    public ResponseEntity<String> handleOrderNotFound(OrderNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
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