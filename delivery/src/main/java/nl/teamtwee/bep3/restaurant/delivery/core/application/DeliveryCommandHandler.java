package nl.teamtwee.bep3.restaurant.delivery.core.application;

import nl.teamtwee.bep3.restaurant.delivery.core.application.command.DeliveryStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.event.DeliveryEvent;
import nl.teamtwee.bep3.restaurant.delivery.core.port.messaging.DeliveryEventPublisher;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum.DELIVERED_SUCCESSFULLY;
import static nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum.DELIVERY_STARTED;

@Service
public class DeliveryCommandHandler {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, DeliveryEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(DeliveryStatus command) throws InterruptedException {
        DeliveryStatusEnum deliveryStatus = command.deliveryStatusEnum;
            Delivery delivery = new Delivery(command.orderId, command.deliveryStatusEnum);
            switch (delivery.getDeliveryStatusEnum()) {
                case READY_TO_BE_DELIVERED:
                    delivery.setDeliveryStatusEnum(DELIVERY_STARTED);
                    deliveryRepository.save(delivery);
                    publishEventsFor(delivery);
                    Thread.sleep(120000);
                    break;
                case DELIVERY_STARTED:
                    delivery.setDeliveryStatusEnum(DELIVERED_SUCCESSFULLY);
                    deliveryRepository.save(delivery);
                    Thread.sleep(120000);
                    break;
            }
    }

    private void publishEventsFor(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }
}
