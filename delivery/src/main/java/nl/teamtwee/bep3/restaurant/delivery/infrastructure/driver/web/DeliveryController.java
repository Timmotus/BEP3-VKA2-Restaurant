package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web;

import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryCommandHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.DeliveryQueryHandler;
import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetDeliveryByStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web.request.DeliveryStatusRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    private final DeliveryCommandHandler deliveryCommandHandler;
    private final DeliveryQueryHandler deliveryQueryHandler;

    public DeliveryController(DeliveryCommandHandler deliveryCommandHandler, DeliveryQueryHandler deliveryQueryHandler) {
        this.deliveryCommandHandler = deliveryCommandHandler;
        this.deliveryQueryHandler = deliveryQueryHandler;
    }

    @GetMapping("/status")
    public List<Delivery> getAllDeliveries(@RequestBody DeliveryStatusRequest deliveryRequest) {
        return this.deliveryQueryHandler.handle(new GetDeliveryByStatus(deliveryRequest.deliveryStatusEnum));
    }

}
