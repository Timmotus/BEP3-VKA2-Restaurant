package nl.teamtwee.bep3.restaurant.delivery.core.application;

import nl.teamtwee.bep3.restaurant.delivery.core.application.command.DeliveryStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.port.messaging.MenuEventPublisher;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web.request.DeliveryStatusRequest;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCommandHandler {

    private final DeliveryRepository deliveryRepository;
    private final MenuEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, MenuEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }

    public Delivery handle(DeliveryStatus delivery) {
        return null; //moet nog gemaakt worden
    }
}
