package nl.teamtwee.bep3.restaurant.delivery.core.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.delivery.core.application.command.CreateDelivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;
import nl.teamtwee.bep3.restaurant.delivery.core.port.messaging.DeliveryEventPublisher;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;

@AllArgsConstructor
@Service
public class DeliveryCommandHandler {
    private final DeliveryRepository repository;
    private final DeliveryEventPublisher eventPublisher;

    public void handle(CreateDelivery command) {
        Delivery delivery = new Delivery(command.orderId);
        // start a random timer for delivery
        publishEventsFor(delivery);
        this.repository.save(delivery);
    }

    private void publishEventsFor(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }
}
