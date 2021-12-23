package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryQueryHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetAllDeliveries;
import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetDeliveryByOrderId;

@AllArgsConstructor
@RestController
@RequestMapping("delivery")
public class DeliveryController {
    private final DeliveryQueryHandler deliveryQueryHandler;

    @GetMapping
    public ResponseEntity<?> getAllDeliveries() {
        return ResponseEntity.ok(this.deliveryQueryHandler.handle(new GetAllDeliveries()));
    }

    @GetMapping(params = { "orderId" })
    public ResponseEntity<?> getByOrderId(@RequestParam UUID orderId) {
        return ResponseEntity.ok(this.deliveryQueryHandler.handle(new GetDeliveryByOrderId(orderId)));
    }
}
