package nl.teamtwee.bep3.restaurant.delivery.core.application;

import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetDeliveryByStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryQueryHandler {

    private final DeliveryRepository repository;

    public DeliveryQueryHandler(DeliveryRepository repository) {
        this.repository = repository;
    }

    public List<Delivery> handle(GetDeliveryByStatus query) {
        return this.repository.findDeliveryByDeliveryStatusEnum(query.getDeliveryStatusEnum());
    }
}
